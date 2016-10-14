val args =List("Hello","to","world")
val csv = args.mkString(",")//csv:String=Hello,to,world

//If you need quotes in result, you can do something like this before mkString:

args.map('"'+_+'"') // List[java.lang.String] = List("Hello", "to", "world")