  //Function currying - uncurried function
//uncurried
def f(i:Int,s:String) = s.toInt + i
//f: (i: Int, s: String)Int
//curried
def f(i:Int) = (s:String)=>s.toInt + i
//f: (i: Int)String => Int
f(5)("2")
//res1: Int = 7

// Partially applied function
///
def createA(i:Int, s:String) = i->s
//createA: (i: Int, s: String)(Int, String)

def createB(param3:Double) = createA(_:Int,s"3 $param3")
//createB: (param3: Double)Int => (Int, String)

createB(4.0)(3)
//res5: (Int, String) = (3,3 4.0)


//Strategy pattern using function types
///
type IntToStringStrategy = (Int) => String

val toStringStrategy: IntToStringStrategy =_.toString

val squareToStringStrategy:IntToStringStrategy = (x:Int)=> (x * 2).toString

//using collect to and partial function filter a collection
val elements = Seq("a", "bb", "cc", "ddd") //> elements  : Seq[String] = List(a, bb, cc, ddd)
def accept = (s: String) => s.length > 1 //> accept: => String => Boolean
def transform = (s: String) => s.toUpperCase //> transform: => String => String
val collectResult = elements collect {case se: String if accept(se) => transform(se)}

//partial function short form
val divide: PartialFunction[Int, Int] = {case d: Int if d != 0 => 42 / d}
//> divide  : PartialFunction[Int,Int] = <function1>
if (divide.isDefinedAt(2)) println(divide(2)) //> 21
val reduceLeft = elements reduceLeft (_.concat(_))


//PartialFunctions composed with 'map'
//val numbers =  (1 to 10).toList (isEven orElse isOdd)

//Partial function on a collection with 'collect'
val list = List(1,2, "a", "b")

val list2 = list.collect{case x:Int => x+1}

//PartialFunction errorHandling
//private val errorHandling: PartialFunction[Throwable, Unit] = {
//  case e: ExecutionException if e.getCause.isInstanceOf[UnknownGeonameException] => error(404, e, logTravelApi404)
//  case e: UnknownGeonameException => error(404, e, logTravelApi404)
//  case e: BadLocationException => error(400, e, logTravelApi400)
//  case e => error(500, e, logTravelApi500)
//}


//function literal
val functionLit = (i: Int) => {i % 2 == 0}

//function literal with explicitly declared return type
val functionLitExpDeclaredType: (Int) => Boolean = i => {i % 2 == 0}

trait Generic
case class Conc() extends Generic
// polymorphic covariant functions
//An upper type bound A <: G declares that type variable A refers to a subtype of type G.
def covariantParams[A<: Generic](g:A, f:A=>Int) = f(g)
val f = (s:Conc) => 5

covariantParams(Conc(),f)


// Call-by-name vs call-by-value



def callByValue(x: Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

def callByName(x: => Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

def something() = {
  println("calling something")
  1 // return value
}

callByValue(something())




//calling something
//  x1=1
//  x2=1

callByName(something())






// calling something
// x1=1
// calling something
// x2=1



//functional composition
//val in = findEventHistory _ andThen (h=>findDuplicate(_,h))


//A nice short-hand for extends Function1[Int, Int] is extends (Int => Int)
class AddOne extends (Int => Int) {
  def apply(m: Int): Int = m + 1
}

//Functional Composition
def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"

//compose makes a new function that composes other functions f(g(x))
val fComposeG = f _ compose g _
fComposeG("yay")
//andThen is like compose, but calls the first function and then the second, g(f(x))
val fAndThenG = f _ andThen g _


//For loop Iteration
case class Obj(f:String, f2:String)
val o =("foo", "bar")
val roadIncidents = for(i<-1 to 5) yield o.copy(f2=i.toInt)





//Idiomatic scala while iteration

//val iterator = Iterator.continually(is.read(buffer, 0, chunkSize)).takeWhile(_ != -1)
//iterator.foreach { l =>
//  baos.write(buffer, 0, l)

}

