trait DomainEvent

/**
  * The revision of an event stream. The revision of an event stream is
  * equal to the number of commits in the event stream.
  */
final case class StreamRevision(value: Long) extends Ordered[StreamRevision] {
  require(value >= 0, "stream revision cannot be negative")

  def previous = StreamRevision(value - 1)
  def next = StreamRevision(value + 1)

  def +(that: Long): StreamRevision = StreamRevision(value + that)
  def -(that: Long): StreamRevision = StreamRevision(value - that)
  def -(that: StreamRevision): Long = this.value - that.value

  override def compare(that: StreamRevision) = value compare that.value
}
object StreamRevision {
  val Initial = StreamRevision(0)
  val Maximum = StreamRevision(Long.MaxValue)
}

case class ApplicationState(posts: Posts = Posts(), users: Users = Users()) {
  def update(event: DomainEvent, revision: StreamRevision) = event match {
    case event: PostEvent => copy(posts = posts.update(event, revision))
    case event: UserEvent => copy(users = users.update(event, revision))
    case _ => sys.error(s"unknown event: $event")
  }

  def updateMany(events: Seq[(DomainEvent, StreamRevision)]) = events.foldLeft(this) {
    case (state, (event, streamRevision)) => state.update(event, streamRevision)
  }
}

sealed trait UserEvent extends DomainEvent {
  def userId: UserId
}

case class UserRegistered(userId: UserId, email: EmailAddress, displayName: String, password: Password) extends UserEvent
case class UserProfileChanged(userId: UserId, displayName: String) extends UserEvent
case class UserEmailAddressChanged(userId: UserId, email: EmailAddress) extends UserEvent
case class UserPasswordChanged(userId: UserId, password: Password) extends UserEvent
case class UserLoggedIn(userId: UserId, token: AuthenticationToken) extends UserEvent
case class UserLoggedOut(userId: UserId) extends UserEvent


//uses the scrypt password hashing algorithm:
case class Password private(hash: String) {
  require(hash.startsWith("$s0$"), "invalid password hash")

  def verify(password: String): Boolean = SCryptUtil.check(password, hash)

  override def toString = "<PASSWORD-HASH>"
}

object Password {
  def fromHash(hash: String): Password = Password(hash)

  def fromPlainText(password: String): Password = Password(SCryptUtil.scrypt(password, 1 << 14, 8, 2))

  implicit val PasswordFormat: Format[Password] = valueFormat(fromHash)(_.hash)
}

