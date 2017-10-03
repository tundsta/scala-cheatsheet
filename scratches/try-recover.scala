import scala.util.{Success, Try}

//try in the middle

//Or recover in the middle:

val sum = for {
  int1 <- Try(Integer.parseInt("one")).recover { case _ => 0 }
  int2 <- Try(Integer.parseInt("2"))
} yield {
  int1 + int2
}

val sum2 = for {
  int1 <- Try(Integer.parseInt("one")).recoverWith { case _ => Success(0) }
  int2 <- Try(Integer.parseInt("2"))
} yield {
  int1 + int2
}


Try(Integer.parseInt("one")).fold(e => s"error: $e" , s => s"done $s")
