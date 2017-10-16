val l = (1 to 10).toList

l.foldLeft(List.empty[Int])((r,c)=>c::r)
l.reduce((r,c) => r + c)
