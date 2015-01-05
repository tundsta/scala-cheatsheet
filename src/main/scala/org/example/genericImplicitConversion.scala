val a = Some(1)
val b:Int = a
implicit def o2i[T](o:Option[T]) = o.getOrElse(sys.error("error"))
val b:Int = a
