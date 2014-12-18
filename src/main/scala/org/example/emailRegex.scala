val reg = """(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b""".r 

reg findFirstIn "yo my name is joe : joe@gmail.com"  match {    
  case Some(email) => println("match: " + email) c
  case None => println("fail") 
}