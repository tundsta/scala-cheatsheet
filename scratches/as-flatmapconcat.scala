import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future

implicit val system = ActorSystem.create("MyActorSystem")
implicit val ec = system.dispatcher
implicit val materializer = ActorMaterializer()

