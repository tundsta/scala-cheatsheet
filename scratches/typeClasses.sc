import scala.xml.Elem

trait XmlConverter[A] {
  def toXml(a: A): Elem
}


case class Movie(name: String, year: Int, rating: Double)

object Converters {

  implicit object MovieConverter extends XmlConverter[Movie] {
    def toXml(a: Movie) = <movie>
      <name>
        {a.name}
      </name>
      <year>
        {a.year}
      </year>
    </movie>
  }

}

object Main {

  import Converters._

  def toXml[A: XmlConverter](a: A) = implicitly[XmlConverter[A]].toXml(a)

  def c = {
    val p = Movie("Inception", 2010, 10)
    toXml(p)
  }
}

Main.c
