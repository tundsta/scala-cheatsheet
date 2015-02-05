trait Prompter1 {

  val prompt = "> "
  val greeting = "Hello world"

  def greet = prompt + greeting
}

val p1 = new Prompter1{}
p1.greet

//split trait as a greeting and prompt

trait GreetingProvider { val greeting = "Hello world" }

trait Prompter2 { self: GreetingProvider =>

  val prompt = "> "

  def greet = prompt + greeting
}

val p2 = new Prompter2 with GreetingProvider
p2.greet
