// implicit values

// trait types

//json4s extract string from JValue
//compact(render(jvalue))



// simple form of regex extractor
val pattern = """([0-9]+) ([A-Za-z]+)""".r
val pattern(count, fruit) = "100 Bananas”
 //count: String = 100
 //fruit: String = Bananas