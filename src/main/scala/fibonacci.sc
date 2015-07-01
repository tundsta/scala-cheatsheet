import scala.annotation.tailrec

def fibonacci(n: Int) = {

  @tailrec
  def go(n:Int, p:Int, c: Int): Int =
   if(n == 0) p else go(n-1, c, p + c)


 go(n, 0, 1)
}

(1 to 10) map fibonacci

