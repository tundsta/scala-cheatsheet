val helloWorldStream: RunnableGraph[NotUsed] =
    Source.single("Hello world")
        .map(s => s.toUpperCase())
.to(Sink.foreach(println))

implicit val actorSystem = ActorSystem("akka-streams-example")
implicit val materializer = ActorMaterializer()

helloWorldStream.run()
