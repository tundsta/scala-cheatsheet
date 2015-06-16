trait Addable[A] {
  def add(x: A): A
}

class AddInt(x: Int) extends Addable[Int] {
  override def add(y: Int) = x + y
}

implicit val addInt: AddInt = new AddInt(30)


implicit val addString: Addable[String] = new Addable[String]  {
  val y = "Suffix"
  override def add(x: String) = x + y
}
//def foo[A](x: A)(implicit ev: Addable[A]): A = ev.add(x)
//A doesn't implement Addable, it just means that there's an Addable for this A, the implicit one you defined above addInt
def foo[A: Addable](x: A) = implicitly[Addable[A]].add(x)


foo(2)//res0: Int = 32
foo("prefix")//res1: String = prefixSuffix
