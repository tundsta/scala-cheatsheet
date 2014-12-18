//tail pattern match
(1 to 10).toList match {
  case x :: rest => println(s"x=$x"); println(s"rest=$rest")
}