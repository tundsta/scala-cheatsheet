//The usual representation of the free monad represents the monadic operations
// in terms of point along with join, instead of the more familiar flatMap,
// but the idea is still the same

sealed trait Free[F[_], A]
final case class Point[F[_], A](a: A) extends Free[F, A]
final case class Join[F[_], A](s: F[Free[F, A]]) extends Free[F, A]
