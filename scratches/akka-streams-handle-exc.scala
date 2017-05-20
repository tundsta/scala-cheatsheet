import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.typesafe.config.ConfigFactory

import scala.util._

implicit val system = ActorSystem.create("MyActorSystem")
implicit val ec = system.dispatcher
implicit val materializer = ActorMaterializer()


Source(List(1, 2, 3)).map { i =>
  if (i == 2) throw new RuntimeException("Please, don't swallow me!") else i

}.runForeach { i =>
  println(s"Received $i")
}.onComplete {
  case Success(_) =>
    println("Done")
  case Failure(e) =>
    println(s"Failed with $e")
}
