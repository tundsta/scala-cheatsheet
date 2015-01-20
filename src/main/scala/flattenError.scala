 Some(List(1,2,3)).flatten
//Cannot prove that Seq[Int] <:< Option[B]
//flatten adopts the type of outer container - you can't fit a list into an option - change type of outer container to be compatible
