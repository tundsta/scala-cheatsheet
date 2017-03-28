//It is common to take an action, like logging a message or updating counters, when a stream terminates.
// The watchTermination element can be used to take an action when the upstream competes with success or failure.
// The following example extends the Greeter WebSocket Service example from the Akka documentation with a watchTermination
// element, to log a message and update session counters when the WebSocket is closed.
def greeter: Flow[Message, Message, Any] =
  Flow[Message].mapConcat {
    case tm: TextMessage =>
      TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
    case bm: BinaryMessage =>
      // ignore binary messages but drain content to avoid the stream being clogged
      bm.dataStream.runWith(Sink.ignore)
      Nil
  }

val websocketRoute =
  path("greeter") {
    val startTime = System.currentTimeMillis()
    val greeterRoute = greeter.watchTermination() { (_, done) =>
      done.onComplete {
        case Success(_) =>
          logger.info("Completed successfully")
          Stats.stats.updateAverageSessionLength(System.currentTimeMillis() - startTime)
        case Failure(ex) =>
          logger.error(s"Completed with failure : $ex")
          Stats.stats.updateAverageSessionLength(System.currentTimeMillis() - startTime)
      }
    }
    handleWebSocketMessages(greeterRoute)
  }

val bindingFuture = Http().bindAndHandle(websocketRoute, "localhost", 8080)
