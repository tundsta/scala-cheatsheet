val isX = (_:String) == "x"
val p = println(_:String) // String => Unit = <function1>
val f0 = (_: Int) + (_: Int)
val f1 = (i: Int) => { i % 2 == 0 }
val asInt = (_:String).toInt
() => { System.getProperty("user.dir") }
val f: (Int) => Boolean = i => { i % 2 == 0 } 
val f: Int => Boolean = i => { i % 2 == 0 } 
val f: Int => Boolean = i => i % 2 == 0
val f: Int => Boolean = _ % 2 == 0


// Example of function composability
def t1 = (s: String) => 5
def t2 = (s: Int) => "bye"
val y = t1 andThen t2

//Functional Composition
def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"

//compose makes a new function that composes other functions f(g(x))
val fComposeG = f _ compose g _
fComposeG("yay")
//andThen is like compose, but calls the first function and then the second, g(f(x))
val fAndThenG = f _ andThen g _
