//The following Akka Streams implementation receives a message, parses it, batches writes to the database
// at 1000 messages—but, in addition, will not buffer messages for more than one second—and will limit the
// number of outstanding, asynchronous writes to the database to 10.

val database = new Database()
val measurementsWebSocketService = Flow[Message].collect {
  case TextMessage.Strict(text) => Future.successful(text)
  case TextMessage.Streamed(textStream) =>
    textStream.runFold("")(_ + _).flatMap(Future.successful)
}
  .mapAsync(1)(identity)
  .map(InsertMessage.parse).groupedWithin(1000, 1 second)
  .mapAsync(10)(database.bulkInsertAsync)
  .map(messages => InsertMessage.ack(messages.last))

val route = path("measurements") {
  get {
    handleWebSocketMessages(measurementsWebSocketService)
  }
}
val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
