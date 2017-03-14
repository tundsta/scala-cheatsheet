
"The following example batches samples, then writes the batched samples to a database, asynchronously. It limits the number of outstanding requests to the database from this client to a maximum of 10. " +
  Source.tick(0 milliseconds, 10 milliseconds, ()).map(_ =>
                                                         Sample(System.currentTimeMillis(),
                                                                random.nextFloat()))
    .groupedWithin(1000, 100 milliseconds)
    .mapAsync(10)(database.bulkInsertAsync)
    .runWith(Sink.ignore)

//This example preserves the order of the elements downstream, which can be important for adjusting watermarks, or generating acknowledgments.
