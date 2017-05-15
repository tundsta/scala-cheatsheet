
//opt.fold(bar)(foo)
//Fold even gives us the additional benefit of being more type-strict than either of the previous alternatives. 

//For instance, while a and b in the example below have a type of Any (with value 2), c fails to compile:

val opt = Option(1)

val a = opt match {
  case Some(x) => x + 1
  case None => "a"
}

val b = opt map (_ + 1) getOrElse "a"

val c = opt.fold("a")(_ + 1 toString)
//fold may not be as readable as the alternatives and a little error-prone, too, since it is not clear what comes first, None or Some?
