// you can use println for debugging purposes, e.g.
// println("this is a debug message")
object Solution {
  def solution(A: Array[Int]): Int = {
    val dec = (_: Int) - 1
    val inc = (_: Int) + 1
    val arrayStart = (_: Int) < 0
    val arrayEnd = (_: Int) == A.length

    def loop(i: Int, next: Int => Int, acc: Int, end: Int => Boolean, f: (Int, Int) => Int): Int = {
      if (end(i))
        acc
      else
        loop(next(i), next, f(acc, A(i)), end, f)

    }

    def loopA(Aindex: Int, acc: Int): Int = {

      if (Aindex < A.length) {

        val nextAcc = Math.abs(
          loop(
            Aindex,
            inc,
            loop(Aindex - 1, dec, 0, arrayStart, (_: Int) - (_: Int)),
            arrayEnd,
            (_: Int) + (_: Int)
          )
        )

        loopA(inc(Aindex), if (acc < nextAcc) acc else nextAcc)
      } else acc
    }
    loopA(1, Int.MaxValue)
  }

}

val a = Array(-3, -1, 2, 4, 3)
//-3 - 8 = 5
Solution.solution(a)

8 - -3