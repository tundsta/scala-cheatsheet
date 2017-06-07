"hey" match {case x@("hey" | "yo") => println(x)}
"yo" match {case x@("hey" | "yo") => println(x)}
