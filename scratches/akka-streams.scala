import akka.actor.ActorSystem
import akka.stream.{ActorAttributes, ActorMaterializer, ActorMaterializerSettings, Supervision}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

implicit val system = ActorSystem.create("MyActorSystem")
implicit val ec = system.dispatcher
implicit val materializer = ActorMaterializer()

import akka.NotUsed
import akka.stream.scaladsl.{Flow, RunnableGraph, Sink, Source}


/**
  * Stage modifiers
  * ---------------
  * .map – transform stream by executing function on each element
  * .flatMap – takes in a collection and flattens
  * .filter – end the stage by returning false, else emit element downstream if true
  * .mapConcat – takes an async list and returns a list (futures to materialised value)
  * .mapConcatAsync(5) – return a list, in a multithreaded output stream (futures)
  * .statefulMapConcat – like mapConcat except it creates the transformation function anew for every materialization eg .statefulMapConcat[T](f: () ⇒ (Out) ⇒ Iterable[T]) () => {this remains stateful} => {this is the transformation fn}
  * .alsoTo – broadcast to another flow (eg logging)
  * .merge – inject a source into existing flow (eg heartbeating) .
  * via – transform this flow to another flow
  * .to – connects a flow to a sink
  * .toMat – requires a PF, but then also provides the Left, Right or Both streams sections of the materialised values
  * .runWith – gives the materialized values of the stages added by runWith() eg flow.to(sink).runWith(source) – end up with Source elements source.via(flow).runWith(sink) – end up with Sink elements .viaMat – ??
  * *
  * Source specific modifiers
  * -------------------------
  * .throttle – restrain element flow from Source eg throttle(1, 5.seconds, 1, ThrottleMode.Shaping)
  * .cycle() – creates a source that continually creates the elements in order eg .cycle(() ⇒ (0 to 11).iterator)
  * .tick – elements are emitted periodically eg tick(5.seconds, 5.seconds, ())"
  **/


val helloWorldStream: RunnableGraph[NotUsed] =
  Source.single("Hello world")
    .map(s => s.toUpperCase())
    .to(Sink.foreach(println))


helloWorldStream.run()

import akka.stream.scaladsl.Source

import scala.util.Random

// random number generator
val s = Source.fromIterator(() => Iterator.continually(Random.nextDouble()))

/**
  * ================================================================================
  * Flattening a stream.
  * ================================================================================
  *
  * This is a common pattern where a stream of A is turned into a stream of Iterable[B].
  * On regular collections this is the well-known flatMap operation.
  * On streams its called mapConcat because its not intended to be used in for-comprehension
  * Also note that mapConcat requires a strict (immutable) collection.
  */

Source('A' to 'E').mapConcat { letter =>
  (1 to 3).map(index => s"$letter$index")
}.runForeach(println)

val fmc = Source('A' to 'E').flatMapConcat { letter =>
  val a = (1 to 3).map(index => s"$letter$index")
  Source(a)
}

val x = Source(1 to 10).flatMapConcat {i => Source((1 to 3).map(_ * i)) }

fmc.runForeach(println)

/**
  * This is a common pattern where a stream of A is turned into a stream of
  * Iterable[B] using a function A => Iterable[B]. However we don’t want a
  * Stream of Iterable[B] but just a stream of B
  */

type Message

object MessageParser {
  def parse(json: String): List[Message] = ???
}

val json = """{ | "id": "c75cb448-df0e-4692-8e06-0321b7703992", | "timestamp": 1486925114, | "measurements": { | "power": 1.7, | "rotor_speed": 3.9, | "wind_speed": 10.1 | } |}""".stripMargin
Source.single(json)
  .map(MessageParser.parse)
  .mapConcat(identity)
  .runWith(Sink.foreach(println))

//This works, but decomposing messages is the perfect usecase for flatMapConcat.
// I find this element is often misunderstood, and it certainly doesn 't have
// the most discoverable or approachable name, but it is the right choice for
// decomposing lists of elements, and it handles the example above in a single
// stage.
Source.single(json).flatMapConcat { message =>
  val measurements = MessageParser.parse(message)
  Source(measurements)
}.runWith(Sink.foreach(println))

/**
  * ===========================================================================
  * mapAsync
  * ===========================================================================
  *
  * Asynchronous computations Writing the batch to a database is most likely an
  * asynchronous operation, which returns a Future.
  * In this case we have the mapAsync operation at out disposal
  *
  * // mapAsync takes a parallelism parameter and a function returning a Future.
  *
  * The parallelism parameter allows us to specify how many simultaneous operations are allowed
  *
  * If the upstream sends too many requests for the mapAsync to handle,
  * back pressure will apply and slow down the upstream without buffering into memory and
  * risking an OutOfMemoryException.
  */

def writeBatchToDatabase(batch: Seq[Int]): Future[Unit] = Future {
  println(s"Writing batch of $batch to database by ${Thread.currentThread().getName}")
}

/**
  * Note that mapAsync delivers the elements in order. However it doesn't necessarily mean
  * that they are going to be written in order into the database as several threads (10) a
  * re running concurrently here"
  */
Source(1 to 1000000)
  .grouped(10)
  .mapAsync(10)(writeBatchToDatabase)
  .runWith(Sink.ignore)


/**
  *  ===========================================================================
  * Parallel Stages
  *   ===========================================================================
  *
  * Choosing which stage can be performed in parallel requires a good understanding of the
  * different operations performed on the pipeline. Executing a stage in parallel is very simple as
  * it just needs to be marked async.
  */
def stage(name: String): Flow[Int, Int, NotUsed] =
  Flow[Int].map { index =>
    println(s"Stage $name processing $index by ${Thread.currentThread().getName}")
    index
  }

/**
  * Here each elements goes through each stage in sequence.
  * 1 goes through stage A,B,C,
  * then 2 goes through A,B,C,
  * then 3  using only a single thread.
  */
Source(1 to 1000000)
  .via(stage("A"))
  .via(stage("B"))
  .via(stage("C"))
  .runWith(Sink.ignore)

/** You may think that this pipeline takes a thread from the pool and runs forever
  * (no async boundary)  its not entirely true.
  * Akka streams engine is able to suspend the stream and put the thread back into the pool.
  *
  * It maintains the illusion of single thread execution although several thread might be used
  * at different times but overall the elements are processed sequentially
  * (as if they were on a single thread).
  */

/**
  * Now lets execute the 3 stages in parallel
  */
Source(1 to 1000000)
  .via(stage("A")).async
  .via(stage("B")).async
  .via(stage("C")).async
  .runWith(Sink.ignore)

/**
  * The elements still go through A, B and C stages sequentially but while one element
  * is in stage B, A may already process the next one.
  * In fact A, B and C don't have their own thread but share a common pool.
  */

/**
  * ===========================================================================
  * Batching
  * ===========================================================================
  *
  * Basically you have a stream of elements and you need to group them together.
  */
Source(1 to 100).grouped(10).runForeach(println)

Source.tick(0.millis, 10.millis, ())
  .groupedWithin(100, 100.millis)
  .map { batch => println(s"Processing batch of ${batch.size} elements"); batch }
  .runWith(Sink.ignore)

/**
  * ================================================================================
  * Idle timeouts
  * ================================================================================
  *
  * Still when communicating with other service it might be useful to detect when a
  * service stops sending requests or emits messages slower than expected.
  * Its often better to fail the stream than keeping these problem silent which often leads
  * to more subtle issues.
  *
  */
Source.tick(0.millis, 1.minute, ())
  .idleTimeout(30.seconds)
  .runWith(Sink.ignore).recover {
  case _: scala.concurrent.TimeoutException => println("No messages received for 30 seconds")
}


/**
  * Error handling
  */
/**
  * ================================================================================
  * recoverWithRetries.
  * ================================================================================
  * This method takes the number of retries (-1 for infinite retries) and
  * a partial function Throwable => Graph. It means we can use it to restart our pipeline
  * (if our source allows it).
  *
  */

def pipeline = Source(1 to 5).map { case 3 => throw new Exception(" three fails") case n => n }
pipeline
  .recoverWithRetries(2, { case _ => pipeline.initialDelay(2.seconds) })
  .runForeach(println)


/**
  * * ================================================================================
  * onComplete.
  * * ================================================================================
  * basic error handling/recovery per stage
  */

Source(List(1, 2, 3)).map { i =>
  if (i == 2) throw new RuntimeException("Please, don't swallow me!") else i

}.runForeach { i => println(s"Received $i")
}.onComplete {
  case Success(_) => println("Done")
  case Failure(e) => println(s"Failed with $e")
}


/**
  * ================================================================================
  * Supervision Strategies
  * ================================================================================
  */
/**
  * There are 3 strategies to choose from:
  *
  * Stop     - completes the stream with failure
  * Resume  - The faulty element is dropped and the stream continues
  * Restart - The faulty element is dropped and the stream continues after restarting the stage
  *
  * recovery strategy can be defined in the ActorMaterializer itself
  *
  * The difference between Resume and Restart is somehow subtle.
  * To understand it we need a stage which maintains a state.
  * fold has a state (the current values depends on the previous elements).
  * On the other hand map has no state.
  * Therefore for a map operation the resuming and restarting strategies have the same effect.
  */
implicit val mat = ActorMaterializer(
  ActorMaterializerSettings(system)
    .withSupervisionStrategy(Supervision.restartingDecider[Supervision.Decider])
)

/**
  * or for any operators of the stream
  */

Source(1 to 5).map { case 3 => throw new Exception("3 is bad") case n => n }
  .withAttributes(ActorAttributes.supervisionStrategy(Supervision.restartingDecider))
  .runForeach(println)


