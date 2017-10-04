

class Covariant[+A]

val x: Covariant[AnyRef] = new Covariant[String]

class Contravariant[-A]

val y: Contravariant[String] = new Contravariant[AnyRef]

//A type can be covariant when it does not call methods on the type that it is
// generic over.
// If the type needs to call methods on generic objects that are passed into it ,
// it cannot be covariant.


type Seq[+A]
type Option[+A]
type Future[+T]

//A type can be contravariant when it does call methods on the type that it is generic over. If the type needs to return values of the type it is generic over, it cannot be contravariant. 

//Functions are the best example of contravariance
//(note that they’re only contravariant on their arguments, and they’re actually covariant on their result).
//Archetypal examples: 
type Function1[- T1, + R]
type CanBuildFrom[- From, - Elem, + To]
type OutputChannel[- Msg]

//A type parameter is invariant when it’s neither covariant nor contravariant.

//Rest assured, the compiler will inform you when you break these rules:

trait T2[+A] {
  def consumeA(a: A) = ???
}

// error: covariant type A occurs in contravariant position
//   in type A of value a
//       trait T[+A] { def consumeA(a: A) = ??? }
//                                  ^

trait T3[-A] {
  def provideA: A = ???
}

// error: contravariant type A occurs in covariant position in
//  type => A of method provided
//       trait T[-A] { def provideA: A = ??? }

trait Collection[+T] {
  def add[U >: T](other: U): Collection[U]
}
