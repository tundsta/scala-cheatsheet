//When using zipWithIndex, the      counter always starts at 0. You can      also use the zip method with a      Stream to create a counter. This      gives you a way to control the starting value:
val days = Seq("Mon,","Tues","Wed","THurs")
for ((day, count) <- days.zip(Stream from 1)) {
  println(s"day $count is $day")
}
