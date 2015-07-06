import scala.concurrent.Future

def retryII[T](n: Int)(block: => Future[T]): Future[T] = {
  val ns: Iterator[Int] = (1 to n).iterator
  val attempts: Iterator[() => Future[T]] = ns.map(_ => () => block)
  val failed: Future[T] = Future.failed(new Exception)
  attempts.foldLeft(failed)((a, block) => a fallbackTo {block()})
}
