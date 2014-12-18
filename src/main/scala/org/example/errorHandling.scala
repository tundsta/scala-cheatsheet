private val errorHandling: PartialFunction[Throwable, Unit] = {
    case e: ExecutionException if e.getCause.isInstanceOf[UnknownGeonameException] => error(404, e, logTravelApi404)
    case e: UnknownGeonameException => error(404, e, logTravelApi404)
    case e: BadLocationException => error(400, e, logTravelApi400)
    case e => error(500, e, logTravelApi500)
  }
