import akka.stream.scaladsl.Source

val json = """{ | "id": "c75cb448-df0e-4692-8e06-0321b7703992", | "timestamp": 1486925114, | "measurements": { | "power": 1.7, | "rotor_speed": 3.9, | "wind_speed": 10.1 | } |}""".stripMargin
Source.single(json).map(MessageParser.parse).mapConcat(identity).runWith(Sink.foreach(println))
//This works, but decomposing messages is the perfect use - case for flatMapConcat.
// I find this element is often misunderstood, and it certainly doesn 't have the most
// discoverable or approachable name, but it is the right choice for decomposing lists of
// elements, and it handles the example above in a single stage.
Source.single(json).flatMapConcat { message =>
  val measurements = MessageParser.parse(message)
  Source (measurements)
}
  .runWith(Sink.foreach(println))
