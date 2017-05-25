//conditional add to collection
val o = Some('z' -> 3)
(Seq('x' -> 1, 'y' -> 2) ++ o).toMap

val n = None
(Seq('x' -> 1, 'y' -> 2) ++ n).toMap

// all pairwise combinations
val chars = Seq('a','b','c','d')
for{ c<-chars; b<-chars } yield c->b

//tail pattern match
(1 to 10).toList match {
  case x :: rest =>
    println(s"x=$x")
    println(s"rest=$rest")
}

// List[Option[A]] => Boolean
Seq(None, Some(2)).flatten.nonEmpty




//collection.fold
//Because fold does not go in any particular order, there are constraints on the start value and thus return value (in all three folds the type of the start value must be the same as the return value).

//The first constraint is that the start value must be a supertype of the object you're folding. In our first example we were folding on a type List[Int] and had a start type of Int. Int is a supertype of List[Int].

// The second constraint of fold is that the start value must be neutral, i.e. it must not change the result. For example, the neutral value for an addition operation would be 0, 1 for multiplication, Nil lists, etc.



val validFold = (1 to 10).toList.fold(0)(_ + _)

case class Foo(x:Int)
val invalidFold = (1 to 10).toList.map(Foo.apply).fold(0)( _ + _)
