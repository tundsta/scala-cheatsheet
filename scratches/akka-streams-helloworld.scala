val helloWorldStream: RunnableGraph[NotUsed] =
    Source.single("Hello world")
        .map(s => s.toUpperCase())
.to(Sink.foreach(println))
