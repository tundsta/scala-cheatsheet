cala> (1 to 10).toList
res0: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

scala> res0.foldLeft(List.empty[Int])((r,c)=>c::r)
res1: List[Int] = List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
