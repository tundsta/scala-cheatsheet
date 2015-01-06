//implicit conversions
object domainEntityTransformerService {
  implicit def  domainEntityFieldToString(s: IncidentField): String = s.toString
  implicit def  domainEntityMapToStringMap(s: Map[IncidentField.Value, AttributeValue]): Map[String, AttributeValue] = s map (kv => (kv._1.toString -> kv._2))
}