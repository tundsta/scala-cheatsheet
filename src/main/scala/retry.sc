import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def retryII[T](n: Int)(block: => Future[T]): Future[T] = {
  val attempts: Iterator[() => Future[T]] = (0 to 1).iterator.map(_ => () => block)
  val failed: Future[T] = Future.failed(new Exception("bork"))
  attempts.foldLeft(failed)((a, block) => a fallbackTo {block()})
}

def retryII[T](n: Int)(block: => Future[T]): Future[T] = {
  val ns: Iterator[Int] = (1 to n).iterator
  val attempts: Iterator[() => Future[T]] = ns.map(_ => () => block)
  val failed: Future[T] = Future.failed(new Exception)
  attempts.foldLeft(failed)((a, block) => a fallbackTo {block()})
}

def retryI[T](n: Int)(block: => Future[T]): Future[T] = {
  if (n == 0) {
    Future.failed(new Exception("Sorry"))
  } else {
    block fallbackTo {retryI(n - 1) {block}}
  }
}

val f = retryI(5)(Future(1 + 5))
f.foreach(println)
