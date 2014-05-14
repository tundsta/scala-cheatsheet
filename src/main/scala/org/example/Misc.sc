import org.scalatest.concurrent.AbstractPatienceConfiguration.PatienceConfig
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span



//Read a text file
io.Source.fromFile("v3feeds.txt").getLines

// implicit values

// trait types

//scalatest define config for asynchronous testing
//
class AsyncTest extends Eventually {
implicit override val patienceConfig =
  PatienceConfig(timeout = Span(1, Seconds), interval = Span(50, Millis))
}

//Access modifier scopes
package society {
package professional {

import society.professional.Executive

class Executive {
  private[professional] var workDetails = null //will be accessible to any class within the enclosing package professional.
  private[society] var friends = null //will be accessible to any class within the enclosing package society.
  private[this] var secrets = null //will be accessible only on the implicit object within instance methods (this).

  def help(another: Executive) {
    println(another.workDetails)
    //    println(another.secrets) //ERROR
  }
}

}

}

//json4s extract string from JValue
//compact(render(jvalue))



// simple form of regex extractor
val pattern = """([0-9]+) ([A-Za-z]+)""".r
val pattern(count, fruit) = "100 Bananas”
 //count: String = 100
 //fruit: String = Bananas

// scala test matching on type
//
//result1 shouldBe a [Tiger]
//result1 should not be an [Orangutan]
//Because type parameters are erased on the JVM, we recommend you insert an underscore for any type parameters when using this syntax. Both of the following test only that the result is an instance of List[_], because at runtime the type parameter has been erased:

//result shouldBe a [List[_]] // recommended
//result shouldBe a [List[Fruit]] // discouraged

