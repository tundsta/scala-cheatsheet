//The usual representation of the free monad represents the monadic operations
// in terms of point along with join, instead of the more familiar flatMap,
// but the idea is still the same

sealed trait Free[F[_], A]
final case class Point[F[_], A](a: A) extends Free[F, A]
final case class Join[F[_], A](s: F[Free[F, A]]) extends Free[F, A]


//Now recall that a monad is defined by two operations,
//Point is not very interesting  it just wraps a monad around a value.
def point[M[_], A](a: A): M[A]
//FlatMap is, however, the distinguishing feature of a monad and it tells us something very important:
// monads are fundamentally about control flow."
def flatMap[M[_], A, B](fa: M[A])(f: A => M[B]): M[B]
