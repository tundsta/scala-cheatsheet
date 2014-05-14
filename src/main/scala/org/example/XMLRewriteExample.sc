// Import the magic libraries

import scala.xml._
import scala.xml.transform._

// Source xml. In Scala, xml is literal.

val xml =
  <user>
    <email>joe@example.com</email>
    <password>secret</password>

    <features>
      <feature id="one"/>
      <feature id="two"/>
      <feature id="three"/>
    </features>

    <foo>
      <bar id="qux" name="caddr"/>
      <bar id="baz" name="cdddr"/>
    </foo>
  </user>

// The problem: replace the "features" list in the above XML
// with a new features list: ["a", "b", "c", "d"]

// Define a function to take a dom and replace the existing
// list of features with a new list of features.

def replaceFeatures(dom: Node, features: List[String]): Node = {

  // You can define objects (or classes) inside functions.

  object replaceFeatures extends RewriteRule {
    // The rule replaces any "features" element with a new
    // features element we build for the occasion.

    override def transform(n: Node): Seq[Node] = n match {

      // match on the "features" element

      case Elem(prefix, "features", attribs, scope, _*) =>

        // XML literals again...

        // Can embed scala inside an XML literal: In this case,
        // apply an anonymous function over the list of features
        // in the original parameter list of the replaceFeatures
        // function. The func turns a feature name into a feature
        // node.

        <features>
          { features map { d => <feature id={d}/> } }
        </features>

      // That which we cannot speak of, we must pass over
      // in silence....

      case other =>
        other
    }

  }

  // Subclass a RuleTransformer (because it's abstract), handing
  // it a vararg list of rules to use (in this case, just one).

  object transform extends RuleTransformer(replaceFeatures)

  // Do the transform. (A scala function's return value is the
  // value of the last expression (and everything's an expression).

  transform(xml)
}

// Transform the xml, replacing the existing feature list
// with a new list constructed from our feature list.

val newXml = replaceFeatures(xml, List("a", "b", "c", "d"))













// Just for fun...
//
// Create a partial function pp so we don't have to have a nasty object
// floating around. pp(xml) is much better than printer.format(xml),
// eh? The underscore stands for the expected value.

val pp = new PrettyPrinter(123, 2).format(_:Node)

// Print before and after

println("before = \n" + pp(xml))














println("after  = \n" + pp(newXml))














