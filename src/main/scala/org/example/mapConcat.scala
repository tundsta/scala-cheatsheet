//map concatenation
Map(1->2,3->4,5->6)
//res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 3 -> 4, 5 -> 6)
res0 + (1->3)
//res3: scala.collection.immutable.Map[Int,Int] = Map(1 -> 3, 3 -> 4, 5 -> 6)