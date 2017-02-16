
import scala.annotation.tailrec
import scala.util._

val department = "[DEP]"
def info(x: String) = println(x)
def fail(x: String) = println(x)
val maxTries = 2


def retryTest(testcase: () => Either[String, String]) = {
  @tailrec
  def retry(request: () => Either[String, String], attempt: Int): (Either[String, String], Int) =
    request() match {
      case Left(_) if attempt <= maxTries => println(s"att: $attempt"); retry(request, attempt + 1)
      case result => result -> attempt
    }

  retry(testcase, attempt = 1) match {
    case (Right(message), attempts) => info(s"Online validation of $department accounts after $attempts attempt(s): $message")
    case (Left(message), _) => fail(s"Online validation of $department accounts iXBRL failed: '$message'")
  }
}

var tried = 0
val fail: Either[String, String] = Left("failllled!")
val pass: Either[String, String] = Right("OK")

def failThenPass: Either[String, String] = {
  println("tried" + tried)
  if (tried < 1) {
    tried = tried + 1
    fail
  } else {
    tried = 0
    pass
  }
}
// 1,2
retryTest(() => fail)
retryTest(() => pass)
retryTest(() => failThenPass)



def retryTry[T](maxtries: Int = 10, backoff: Int = 1000)(testcase: => Try[T]) = {
  @tailrec
  def retry(request: => Try[T], attempt: Int): (Try[T], Int) = request match {
      case Failure(_) if attempt > 0 => println(s"att: $attempt"); retry(request, attempt - 1)
      case result => result -> attempt
    }

  retry(testcase, maxtries)
}
