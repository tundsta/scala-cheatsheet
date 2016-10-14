Some("").filter(_.trim.length>0)
res11: Option[String] = None

case class Secretary(name:String)

val bob = Option(Secretary("Bob"))
val noone = Option(Secretary(""))
val name = (s:Option[Secretary]) => s.map(_.name).filter(_.trim.length > 0)

name(b) //res5: Option[String] = Some(Bob)
name(noone) //res6: Option[String] = None
