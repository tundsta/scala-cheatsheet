val isX = (_:String) == "x"
val f = (_: Int) + (_: Int)
val f = (i: Int) => { i % 2 == 0 }
val f: (Int) => Boolean = i => { i % 2 == 0 } 
val f: Int => Boolean = i => { i % 2 == 0 } 
val f: Int => Boolean = i => i % 2 == 0
val f: Int => Boolean = _ % 2 == 0
