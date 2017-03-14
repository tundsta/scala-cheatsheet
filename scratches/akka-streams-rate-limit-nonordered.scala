
//Akka Streams also provides the mapAsyncUnordered mechanism to limit the rate, without necessarily preserving downstream order.
Source.tick(0 milliseconds, 10 milliseconds, ()) .map(_ => Sample(System.currentTimeMillis(),
                                                                  random.nextFloat()))
  .groupedWithin(1000, 100 milliseconds)
  .mapAsyncUnordered(10)(database.bulkInsertAsync)
  .runWith(Sink.ignore)"
