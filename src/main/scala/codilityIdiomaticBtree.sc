import scala.annotation.tailrec

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

case object Empty extends Tree[Nothing]

object Solution {
  //return the number of visible nodes
  def solution(T: Tree[Int]): Int = {
    //if the path from the root does not contain a node exceeding V
    // no node lower than the root is visible
    // the root is always visible

    def countNodes(tree: Tree[Int], root: Int, max: Int): Int = tree match {

      case Branch(x, l, r)                  => (if (x >= max && x >= root) 1 else 0) + countNodes(l, root, max) + countNodes(r, root, max)
      case Leaf(x) if x >= max && x >= root => 1
      case Leaf(_)                          => 0
      case Empty                            => 0

    }

    T match {
      case Branch(x, _, _) => countNodes(T, x, x)
      case Leaf(_)         => 1
      case Empty           => -1
    }
  }
}

val t =
  Branch(
    5,
    Branch(
      3,
      Leaf(20),
      Leaf(21)
    ),
    Branch(
      10,
      Leaf(1),
      Empty
    )
  )
assert(Solution.solution(Branch(5, Leaf(6), Leaf(4))) == 2)
assert(Solution.solution(Branch(5, Leaf(6), Empty)) == 2)
assert(Solution.solution(t) == 4)
assert(Solution.solution(Leaf(3))  == 1)
assert(Solution.solution(Empty) == -1)