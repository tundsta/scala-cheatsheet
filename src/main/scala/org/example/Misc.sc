

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



