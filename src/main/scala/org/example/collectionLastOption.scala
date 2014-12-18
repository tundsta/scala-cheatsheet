 //Retrieve the last element from an empty collection - lastOption
res3.sortBy(_.t).last
java.util.NoSuchElementException
     at scala.collection.LinearSeqOptimized$class.last(LinearSeqOptimized.scala:135)

res3.sortBy(_.t).lastOption
res6: Option[inc] = None