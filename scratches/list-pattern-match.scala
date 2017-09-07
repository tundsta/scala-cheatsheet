//tail pattern match
(1 to 10).toList match {
  case x :: rest => println(s"x=$x"); println(s"rest=$rest")
}

(1 to 10).toList match {
  case _ :: x :: rest => println(s"x=$x, rest=$rest")
}

def f(s: String): String = {
  def ff(l: List[String]) = l match {
    case Nil      => sys.error("error")
    case x :: Nil => x.capitalize
    case x :: xs  => x.capitalize + f(xs.mkString)
  }

  ff(s.split(" ").toList)
}
f("")
f("Date from")
