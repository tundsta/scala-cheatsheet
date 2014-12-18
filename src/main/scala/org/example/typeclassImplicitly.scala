// for a given typeclass  
trait CharIso[T] {
  def toChar(t: T): Char
  def fromChar(c: Char): T
}
 
// provide the following apply method
// so that you can write: CharIso[T].toChar(...) instead of implicitly[CharIso[T]].toChar(...)  
object CharIso {
  def apply[T : CharIso]: CharIso[T] = implicitly[CharIso[T]]
}
