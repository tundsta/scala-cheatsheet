def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {

  def go(i: Int): Boolean = {
    if (i >= as.length - 1) true
    else if (ordered(as(i), as(i + 1))) go(i + 1)
    else false
  }

  go(0)
}



val a = (_: Int) < (_: Int)

isSorted(Array(1, 2, 3), a)

isSorted(Array(3, 2, 1), a)

