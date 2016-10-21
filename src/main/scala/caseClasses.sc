// Case classes are primarily intended to create “immutable records” that you can easily use in pattern-matching expressions.
case class Small(a: Int, b: String)


//This small line gives us a lot. We have field accessors, a constructor, equality, hash code, copy, and product methods, but also these two methods:

//unapply - from Product2 (via Tuple2); and
//tupled - from Function2.

//takes us from an instance of Small to the tuple that makes up the instance.
Small.unapply _ // Small => Option[(Int, String)] = <function1>

//go the other way
Small.tupled //((Int, String)) => Small = <function1>

