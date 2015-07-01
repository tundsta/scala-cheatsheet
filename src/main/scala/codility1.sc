  object Solution {
    def solution(N: Int): Int = {
      if(Math.abs(N) < 10 ) 1
      else
      N.toString.map(_.asDigit).distinct.dropWhile(x => x == 0 || x == -0).size
    }
  }

Solution.solution(122340)
Solution.solution(-1234)
Solution.solution(0)
Solution.solution(-10)

Math.abs(-2147483648)//.toString.map(_.asDigit)

val y = -2147483648
Math.abs(y)