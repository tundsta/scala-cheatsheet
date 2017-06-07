//tail pattern match
(1 to 10).toList match {
  case x :: rest => println(s"x=$x"); println(s"rest=$rest")
}

(1 to 10 ).toList match {
  case _: :: x :: rest => println(s"x=$x, rest=$rest")
}
