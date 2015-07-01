

class Tree(var x: Int, var l: Tree, var r: Tree)

object Solution {
  //return the number of visible nodes
  def solution(T: Tree): Int = {
    def isLeaf(t: Tree) = t.l == null && t.r == null
    //if the path from the root does not contain a node exceeding V
    // no node lower than the root is visible
    // the root is always visible


    def countNodes(tree: Tree, root: Int, max: Int): Int = {

      def tcount(t: Tree) = if (t != null) {
        (if (t.x >= root || t.x >= max) 0 else 1) + countNodes(t, root, t.x)
      } else 0

      tcount(tree.l) + tcount(tree.r)
    }

    countNodes(T, T.x, T.x) + 1
  }
}

val t = new Tree(5,
  new Tree(3,
    new Tree(20, null, null),
    new Tree(21, null, null)),
  new Tree(10,
    new Tree(1, null, null), null))

Solution.solution(t)
Solution.solution(new Tree(3, null, null))