//fibonacci
def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)
val fibs = fibFrom(1,1)
fibFrom(0,1).take(10).toList

//dates
def dates(d: DateTime): Stream[DateTime] = d #:: dates(d.plusDays(1))
dates.take(10).toList
dates(DateTime.now).take(10).toList
