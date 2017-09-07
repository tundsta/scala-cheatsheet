/**
  * Stage modifiers
  * ---------------
  * .map – transform steam by executing function on each element
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

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{RunnableGraph, Sink, Source}

val helloWorldStream: RunnableGraph[NotUsed] =
  Source.single("Hello world")
  .map(s => s.toUpperCase())
  .to(Sink.foreach(println))

implicit val actorSystem = ActorSystem("akka-streams-example")
implicit val materializer = ActorMaterializer()

helloWorldStream.run()

import akka.stream.scaladsl.Source

import scala.util.Random

Source.fromIterator(() => Iterator.continually(Random.nextDouble()))

