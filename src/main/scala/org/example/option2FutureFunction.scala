def toFuture[A](option: Option[A]) = option.map(Future.successful).getOrElse(Future.failed(new Exception(s"Error ")))
