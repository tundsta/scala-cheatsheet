absrract class blah[T] (implicit ct: ClassTag[T]) { def blockType : Class[_] = implicitly[reflect.ClassTag[T]].runtimeClass }
object objIn extends blah[Action]
objIn.blockType //Cl


abstract class blah[T <: String] (implicit ct: TypeTag[T]) { def blockType = implicitly[TypeTag[T]] }
object objIn extends blah[Action]
object objIn2 extends blah[String]

objIn.blockType.tpe // type alias typeTag
objIn2.blockType.tpe // conventional type typeTag

//example method to retrieve typeTag for an instance object
 def getType[T: TypeTag](obj: T) = typeTag[T]

import scala.reflect.runtime.universe._

//We need parameters that carry the TypeTag from where the type is concrete to where the typeTag is needed

def getInnerType[T](list:List[T])(implicit tag:TypeTag[T]) = tag.tpe.toString
al stringList: List[String] = List("A")
val stringName = getInnerType(stringList)
println( s"a list of $stringName")
//a list of java.lang.String

