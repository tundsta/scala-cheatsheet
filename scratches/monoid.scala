//TWe first denote the API in a module Monoid[T] and then define the laws of the algebra.

trait Monoid[T] {
  def zero: T

  def op(t1: T, t2: T): T
}

//And here are the laws that a monoid needs to satisfy:
// 1. Left identity – op(zero, t) == t
// 2. Right identity – op(t, zero) == t
// 3. Associativity – op(t1, op(t2, t3)) == op(op(t1, t2), t3)
