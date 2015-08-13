import play.api.data.format.Formats
import play.api.libs.json.Json

case class Location(lat: Double, long: Double)
case class Location2(lat: Double, long: Double, i:Option[Seq[Int]])

implicit def format = Json.format[Location]
implicit def format2 = Json.format[Location2]

val json = Json.toJson(Location(2.0,2.3))

json.as[Location2]
