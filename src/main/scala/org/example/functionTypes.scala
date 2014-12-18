//Strategy pattern using function types
///
type IntToStringStrategy = (Int) => String

val toStringStrategy: IntToStringStrategy =_.toString

val squareToStringStrategy:IntToStringStrategy = (x:Int)=> (x * 2).toString