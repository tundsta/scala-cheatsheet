package org.example

object Collections {
  //nadd element to immutable Map & k/v replacement
  val m1 = Map(1 -> 2, 3 -> 4, 5 -> 6)            //> m1  : scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4, 5 -> 6)

  m1 + (1 -> 3)                                   //> res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 3, 3 -> 4, 5 -> 6)
}