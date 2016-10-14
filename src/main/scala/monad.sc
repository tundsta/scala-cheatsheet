trait Monoid[A] {
  def empty: A
  def append(v1: A, v2: A): A
}

def empty[A: Monoid] = implicitly[Monoid[A]].empty
def append[A: Monoid](v1: A, v2: A) = implicitly[Monoid[A]].append(v1, v2)

implicit val MonoidInt = new Monoid[Int] {
  def empty = 0
  def append(v1: Int, v2: Int) = v1 + v2
}

append(1, append(2, empty[Int]))//res2: Int = 3