//type inference of multiple arguments of the same type (ellipse in java)
varargTest(List(1,2,3):_*)
def varargTest(ints:Int*) { ints.foreach(println) }
