import akka.stream._

import scala.util.control.NonFatal
object log { def error(t:Throwable,s:String) = ???}

val decider: Supervision.Decider = {
  case NonFatal(ex) =>
    log.error(ex, s"OpeningMeterReadingEmailStreamController caught error: $ex - restarting stream")
    Supervision.Restart
}
