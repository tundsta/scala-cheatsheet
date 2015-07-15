val m = Map('a'->"hello", 'b'->"goodbye")
m - 'a' //res0: Map(b -> goodbye)


val flattenMapValues = Map("key1" -> Some("value1"), "key2" -> None, "key3" -> Some("value3")).collect {
  case (key, Some(value)) => key -> value }

Map(1->2,3->4,5->6) + (1->3)
//res3: scala.collection.immutable.Map[Int,Int] = Map(1 -> 3, 3 -> 4, 5 -> 6)
