case class Test(x:Int)
object Test { def apply(s:String)(d:Double):Test = Test(d.toInt * s.toInt) }

Test("3")(5)