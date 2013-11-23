package org.example

object Collections extends App {
  println("===========================\nadd element to immutable Map & k/v replacement")
  val m1 = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1)
  //res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1 + (1 -> 3))

  println("============================\nusing collect to filter a collection")
  val elements = Seq("a", "bb", "cc", "ddd")
  println(elements)
  def accept = (s: String) => s.length > 1
  def transform = (s: String) => s.toUpperCase
  val collectResult = elements collect { case se: String if accept(se) => transform(se) }
  println(collectResult)
}
