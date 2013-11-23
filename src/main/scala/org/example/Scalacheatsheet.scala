package org.example

object Collections extends App {
  println("\n===========================\nadd element to immutable Map & k/v replacement")
  val m1 = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1)
  //res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4, 5 -> 6)
  println(m1 + (1 -> 3))
}

object Functions extends App {
   println("\n============================\nusing collect to and partial function filter a collection")
  val elements = Seq("a", "bb", "cc", "ddd")
  println(elements)
  def accept = (s: String) => s.length > 1
  def transform = (s: String) => s.toUpperCase
  val collectResult = elements collect { case se: String if accept(se) => transform(se) }
  println(collectResult)
  
  
println("\n======================\npartial function short form")
val divide: PartialFunction[Int, Int] = { case d: Int if d != 0 => 42 / d}
  if(divide.isDefinedAt(2)) println(divide(2))
}
