package org.example

object Scalacheatsheet extends App {
  println("Hello, scala-cheatsheet")

  println("add element to immutable Map & k/v replacement")
  val m1 = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1)
  //res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1 + (1 -> 3))

}
