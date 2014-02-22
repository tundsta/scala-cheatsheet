// implicit values

// trait types

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