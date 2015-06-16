//Monads let you compose functions that don’t compose well, such as functions that have side effects.

import scala.concurrent.Future
 
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

