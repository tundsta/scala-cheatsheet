import scala.util.{Failure, Success, Try}

//Try/ Monad
//Try will let you recover from exceptions at any point in the chain, so you can defer recovery to the end

val sum = (for {
  int1 <- Try(Integer.parseInt("one"))
  int2 <- Try(Integer.parseInt("two"))
} yield {
  int1 + int2
}) recover {
  case e => 0
}
//sum: scala.util.Try[Int] = Success(0)


//Processing a seq of Trys
val maybeInts: Seq[scala.util.Try[Int]] = List(
  Success(1),
  Success(2),
  Failure(new java.lang.NumberFormatException("""For input string: "a""")),
  Success(3),
  Success(4))
maybeInts map {
  case Success(s) => s"int found: $s";
  case Failure(f) => s"int error: $f"
}
// returns List(int found: 1, int found: 2, int error: java.lang.NumberFormatException: For input string: "a", int found: 3, int found: 4)
maybeInts
