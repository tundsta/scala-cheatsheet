Dog <: Animal implies Seq[Dog] <: Seq[Animal]
//Functions are the best example of contravariance
//(note that they’re only contravariant on their arguments, and they’re actually covariant on their result).

//A type can be covariant when it does not call methods on the type that it is generic over. If the type needs to call methods on generic objects that are passed into it , it cannot be covariant. 
//Archetypal examples:
Seq[+A], Option[+A], Future[+T]

//A type can be contravariant when it does call methods on the type that it is generic over. If the type needs to return values of the type it is generic over, it cannot be contravariant. 

//Archetypal examples: 
Function1[-T1, +R], CanBuildFrom[-From, -Elem, +To], OutputChannel[-Msg]

//Rest assured, the compiler will inform you when you break these rules:

trait T[+A] { def consumeA(a: A) = ??? }
// error: covariant type A occurs in contravariant position
//   in type A of value a
//       trait T[+A] { def consumeA(a: A) = ??? }
//                                  ^

trait T[-A] { def provideA: A = ??? }
// error: contravariant type A occurs in covariant position in
//  type => A of method provided
//       trait T[-A] { def provideA: A = ??? }

trait Collection[+T] {  

	def add[U >: T](other: U): Collection[U] 
}
