import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

// if you have a case class
case class Location(lat: Double, long: Double)

// creating an overloaded apply
object Location {
  def apply(a:Int,b:Int):Location = Location(a,b)
}
// when you create the default formatter
implicit val reads = Json.format[Location]

// this generates the following unmarshaller
implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
    (JsPath \ "long").read[Double]
  )( Location.apply _)

// which can't find the right apply method so you need to define the apply method parameter signature:


implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
    (JsPath \ "long").read[Double]
  )( Location.apply(_:Double,_:Double))

