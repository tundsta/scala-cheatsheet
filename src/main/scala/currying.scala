//Function currying - uncurried function
//uncurried
def f(i: Int, s: String) = s.toInt +  i//f: (i: Int, s: String)Int
//curried
def f(i: Int) = (s: String) => s.toInt + i //f: (i: Int)String => Int
f(5)("2")
//res1: Int = 7



//Methods with multiple parameter lists become curried functions:

  scala> def plus(a: Int)(b: Int): Int = a + b
plus: (a: Int)(b: Int)Int

scala> plus _
res11: Int => (Int => Int) = <function1>