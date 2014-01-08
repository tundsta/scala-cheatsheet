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

