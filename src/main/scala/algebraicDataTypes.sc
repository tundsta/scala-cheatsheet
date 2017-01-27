// Product type - A has a B and C (logical AND)
final case class A(b:B, c:C)

// Sum Type - A is a B or C (logical OR)
sealed trait A
final case class B() extends A
final case class C() extends A
type Id = String
sealed trait Visitor {
  id:Id
}

final case class Anonymous(id: Id) extends Visitor
case class User(id: Id, email: Email) extends Visitor

//Calculation is a success or Failure (logical OR)
sealed trait Calculation {
  def add(value:Int): Calculation = this match {
    case Success(v) => Success(v + value)
    case Failure(msg) => Failure(msg)
  }
}

final case class Success(value: Int) extends Calculation
final case class Failure(msg: String) extends Calculation

//Structural recursion - structure of the code follows structure of the data base
//trait pattern matching(simpler with less repetition) and polymorphism

//Pattern matching
sealed trait A {
  def doSomething: H = {
    this match {
      case B(d, e) => doB(d, e)
      case C(f, g) => doC(f, g)
    }
  }

  final case class B(d: D, e: E) extends A
  final case class C(f: F, g: G) extends A
