// an exponential-backoff-and-retry supervisor-actor can be used to
// supervise the simulated wind-turbine actor.
// A backoff-and-retry supervision-strategy is not a pattern offered by
// the Akka Streams API, but it is a pattern offered by Akka Actors.

import akka.actor.ActorSystem
import akka.pattern.BackoffSupervisor

implicit val system = ActorSystem()
val id = 123

val supervisor = BackoffSupervisor.props(
  Backoff.onFailure(
    someactor.props(id, Config.endpoint),
    childName = id,
    minBackoff = 1 second,
    maxBackoff = 30 seconds,
    randomFactor = 0.2
  ))

system.actorOf(supervisor, name = s"$id-backoff-supervisor")


