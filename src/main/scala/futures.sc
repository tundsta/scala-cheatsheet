//Monads let you compose functions that don’t compose well, such as functions that have side effects.

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.Success

// Make 3 sequential async calls
for {
  foo <- WS.url("http://foo.com").get()
  bar <- WS.url("http://bar.com").get()
  baz <- WS.url("http://baz.com").get()
} yield {
  // Build a Result using foo, bar, and baz

  Ok(...)
}

// Make 3 parallel async calls
val fooFuture = WS.url("http://foo.com").get()
val barFuture = WS.url("http://bar.com").get()
val bazFuture = WS.url("http://baz.com").get()

for {
  foo <- fooFuture
  bar <- barFuture
  baz <- bazFuture
} yield {
  // Build a Result using foo, bar, and baz

  Ok(...)
}


val aa = Future {
  Thread.sleep(5000)
  println("returning 1")
  1
}
val bb = Future {
  Thread.sleep(1000)
  println("returning 3")
  3
}


val cc = for {
  maybea <- aa
  maybeb <- bb
} yield {
  maybea + maybeb
}



// Handle Exceptions in Futures by logging them and returning a fallback value
def withErrorHandling[T](f: Future[T], fallback: T): Future[T] = {
  f.recover { case t: Throwable =>
    println("Something went wrong!", t)
    fallback
  }
}

val myFuture = someAsyncIO()
val myFutureWithFallback = withErrorHandling(myFuture, "fallback value")


//Future.sequence transforms a Traversable[Future[T]] into a Future[Traversable[T]]
val a = Seq(Future(1), Future(1), Future(2), Future(3))
val b = Future.sequence(a)
val c = b.map(_.sum)

c.value //Option[scala.util.Try[Int]] = Some(Success(7))


//enable Future[Option[_]] composition
None
  .map(_ => Future.successful("success"))
  .getOrElse(Future.failed(new Exception(s"Error ")))


def findId(login: String): Future[Option[Int]] = Future.successful(Option(1))
def findName(id: Int): Future[Option[String]] = Future.successful(Option("Robert"))
def findFriends(name: String): Future[Option[List[String]]] = Future.successful(Option(List("loicd", "tpolecat")))

for {
  id <- findId("TunaBoo")
  name <- id.map(findName).getOrElse(Future.successful(Option.empty))
  friends <- name.map(findFriends).getOrElse(Future.successful(Option.empty))
} yield friends

// schedule a future
//
def delayedFuture[T](delay: FiniteDuration)(block: => T)(implicit executor: ExecutionContext, system: ActorSystem): Future[T] = {
  val promise = Promise[T]

  system.scheduler.scheduleOnce(delay) {
    try {
      val result = block
      promise.complete(Success(result))
    } catch {
      case t: Throwable => promise.failure(t)
    }
  }
  promise.future
}
