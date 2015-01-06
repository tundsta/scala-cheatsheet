def varargTest(ints:Tuple2[String,String]*) { ints.foreach(println) }
varargTest: (ints: (String, String)*)Unit

Replaying: varargTest(("q","e"),("a","b"))
(q,e)
(a,b)

Replaying: varargTest(("q","e"),("a"->"b"))
(q,e)
(a,b)

Replaying: varargTest(("q"->"e"),("a"->"b"))
(q,e)
(a,b)
