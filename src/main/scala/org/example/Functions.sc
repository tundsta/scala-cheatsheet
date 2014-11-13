




//using collect to and partial function filter a collection
val elements = Seq("a", "bb", "cc", "ddd") //> elements  : Seq[String] = List(a, bb, cc, ddd)
val accept = (s: String) => s.length > 1 //> accept: => String => Boolean
val transform = (s: String) => s.toUpperCase //> transform: => String => String
val collectResult = elements collect {case se: String if accept(se) => transform(se)}

//partial function short form
val divide: PartialFunction[Int, Int] = {case d: Int if d != 0 => 42 / d}
//> divide  : PartialFunction[Int,Int] = <function1>
if (divide.isDefinedAt(2)) println(divide(2)) //> 21
val reduceLeft = elements reduceLeft (_.concat(_))


//PartialFunctions composed with 'map'
val numbers =  (1 to 10).toList (isEven orElse isOdd)

//Partial function on a collection with 'collect'

val list2 = List(1,2, "a", "b").collect{case x:Int => x+1}






