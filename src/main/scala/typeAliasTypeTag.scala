import scala.reflect.ClassTag
import scala.reflect.runtime.universe.{ TypeTag, typeOf }

type Action = String

abstract class blah[T] (implicit ct: ClassTag[T]) { def blockType : Class[_] = implicitly[reflect.ClassTag[T]].runtimeClass }
object objIn extends blah[Action]
objIn.blockType //Cl


abstract class blah[T <: String] (implicit ct: TypeTag[T]) { def blockType = implicitly[TypeTag[T]] }
object objIn extends blah[Action]
object objIn2 extends blah[String]

objIn.blockType.tpe // type alias typeTag
objIn2.blockType.tpe // conventional type typeTag

//example method to retrieve typeTag for an instance object
 def getType[T: TypeTag](obj: T) = typeTag[T]