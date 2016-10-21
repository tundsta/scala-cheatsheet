//implicit conversions
object domainEntityTransformerService {
  implicit def  domainEntityFieldToString(s: IncidentField): String = s.toString
  implicit def  domainEntityMapToStringMap(s: Map[IncidentField.Value, AttributeValue]): Map[String, AttributeValue] = s map (kv => (kv._1.toString -> kv._2))
}


//an implicit can both an implicit conversion and an implicit parameter
//getIndex can receive any object, as long as there is an implicit conversion available from its class to Seq[T]
def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)

getIndex("abc", 'a')
// view bounds -  syntactic sugar for above
def getIndex[T, CC <% Seq[T]](seq: CC, value: T) = seq.indexOf(value)
