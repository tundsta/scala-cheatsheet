// Partially applied function
///
def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _ //supply none of the three required arguments
def createA(i:Int, s:String) = i->s
//createA: (i: Int, s: String)(Int, String)

val createB(param3:Double) = createA(_:Int,s"3 $param3")
//createB: (param3: Double)Int => (Int, String)

createB(4.0)(3)
//res5: (Int, String) = (3,3 4.0)



//Default arguments are ignored for the purposes of η-expansion; it is not possible to use named arguments to simulate partial application.

 def foo(n: Int = 3, s: String) = s * n
//foo: (n: Int, s: String)String

 foo _
 //res19: (Int, String) => String = <function2>

   foo(42) _
//  <console>:9: error: not enough arguments for method foo: (n: Int, s: String)String.
//    Unspecified value parameter s.
//    foo(42) _