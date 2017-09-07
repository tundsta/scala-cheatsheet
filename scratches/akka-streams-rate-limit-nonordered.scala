
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

object database {
  def bulkInsertAsync(s: Seq[Sample]) = Future(s)
}

case class Sample(time: Long, f: Float)

//Akka Streams also provides the mapAsyncUnordered mechanism to limit the rate,
// without necessarily preserving downstream order.
Source
.tick(0 milliseconds, 10 milliseconds, ())
.map(_ => Sample(System.currentTimeMillis(), Random.nextFloat()))
.groupedWithin(1000, 100 milliseconds)
.mapAsyncUnordered(10)(database.bulkInsertAsync)
.runWith(Sink.ignore)
