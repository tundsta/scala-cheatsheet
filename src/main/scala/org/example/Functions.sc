package org.example

object Functions {
 //using collect to and partial function filter a collection
  val elements = Seq("a", "bb", "cc", "ddd")      //> elements  : Seq[String] = List(a, bb, cc, ddd)
  def accept = (s: String) => s.length > 1        //> accept: => String => Boolean
  def transform = (s: String) => s.toUpperCase    //> transform: => String => String
 
  val collectResult = elements collect { case se: String if accept(se) => transform(se) }
                                                  //> collectResult  : Seq[String] = List(BB, CC, DDD)
  //partial function short form
  val divide: PartialFunction[Int, Int] = { case d: Int if d != 0 => 42 / d }
                                                  //> divide  : PartialFunction[Int,Int] = <function1>
  if (divide.isDefinedAt(2)) println(divide(2))   //> 21
}