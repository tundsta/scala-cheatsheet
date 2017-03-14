//Scheduling periodic events is quite easy with Akka Streams. The following example combines a stream
// of regular measurement samples with a status message, generated once a minute.
val status = Source.tick(0 minute, 1 minute, ()) .map(_ => Status())
Source.tick(0 milliseconds, 1 second, ())
  .map(_ => Sample(System.currentTimeMillis(), random.nextFloat())) .merge(status)
  .runWith(Sink.foreach(println))
