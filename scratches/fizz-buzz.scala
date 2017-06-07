(1 to 100) map { n => (n % 3 == 0, n % 5 == 0, n) } map {
  case (true, true, n) => (n, "FizzBuzz")
  case (true, false, n) => (n, "Fizz")
  case (false, true, n) => (n, "Buzz")
  case (false, false, n) => (n, ())
} foreach println

(1 to 100) map { n =>
  (n % 3 == 0, n % 5 == 0) match {
    case (true, true) => "FizzBuzz"
    case (true, false) => "Fizz"
    case (false, true) => "Buzz"
    case (false, false) => n
  }
}
