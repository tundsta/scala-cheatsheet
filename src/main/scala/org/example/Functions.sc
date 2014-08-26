




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

//function literal
val functionLit = (i: Int) => {i % 2 == 0}

//function literal with explicitly declared return type
val functionLitExpDeclaredType: (Int) => Boolean = i => {i % 2 == 0}

//functional composition
//val in = findEventHistory _ andThen (h=>findDuplicate(_,h))


//Functional Composition
def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"

//compose makes a new function that composes other functions f(g(x))
val fComposeG = f _ compose g _
fComposeG("yay")
//andThen is like compose, but calls the first function and then the second, g(f(x))
val fAndThenG = f _ andThen g _
