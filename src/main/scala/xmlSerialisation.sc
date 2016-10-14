case class XmlTest(f1:String, f2:Int){ 
     def toXml = <XmlTest><f1>{f1}</f1><f2>{f2}</f2></XmlTest>
 }
 
val test = XmlTest("this is a test", 123)
test.toXml //returns scala.xml.Elem = <XmlTest><f1>this is a test</f1><f2>123</f2></XmlTest>