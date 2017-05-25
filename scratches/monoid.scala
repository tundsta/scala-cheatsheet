//TWe first denote the API in a module Monoid[T] and then
// define the laws of the algebra.


//And here are the laws that a monoid needs to satisfy:
// 1. Left identity – op(zero, t) == t
// 2. Right identity – op(t, zero) == t
// 3. Associativity – op(t1, op(t2, t3)) == op(op(t1, t2), t3)
//
trait Monoid[A] {
  def empty: A
  def append(v1: A, v2: A): A
}

def empty[A: Monoid] = implicitly[Monoid[A]].empty
def append[A: Monoid](v1: A, v2: A) = implicitly[Monoid[A]].append(v1, v2)

implicit val MonoidInt = new Monoid[Int] {
  def empty = 0
  def append(v1: Int, v2: Int) = v1 + v2
}

append(1, append(2, empty[Int]))
