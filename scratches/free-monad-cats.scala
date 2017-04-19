//In Cats, the Free monad is already defined for us, and we can ‘lift’ a type constructor into the it with Free.liftF. Let us write a little bit of boilerplate to help us do that:

object ActionFree {
    def readData(port: Int): Free[Action, String] = Free.liftF(ReadData(port))
      def transformData(data: String): Free[Action, String] = Free.liftF(TransformData(data))
        def writeData(port: Int, data: String): Free[Action, Unit] = Free.liftF(WriteData(port, data))
}
//The general pattern above is to turn an F[A] into a Free[F, A] so we have access to the monadic operations such as flatMap provided by Free.

//Now we can do this:

import ActionFree._
val program = for {
    d <- readData(123)
      t <- transformData(d)
        _ <- writeData(789, t)
} yield ()
// program: Free[Action, Unit]
// We can then take program and in turn compose it with other structures with the same signature.
