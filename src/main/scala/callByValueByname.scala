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