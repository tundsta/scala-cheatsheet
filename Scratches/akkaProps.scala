class SomeActor(parameter: Long)(implicit service:Service) extends Actor {
  def receive = {
    case message => // Do your stuff
  }
}

object SomeActor {
  def props(parameter: Long)(implicit service:Service) = Props(new SomeActor(parameter))
}

implicit val service:Service = new Service()
val someLong = 3
val ref = context.actorOf(SomeActor.props(someLong)), "actor")
