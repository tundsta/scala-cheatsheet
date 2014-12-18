//for expression with 2 generators translates into a flatMap


    for {
      code <- locationStnCodes
      keys <- incidentsByStnCode.keys if (keys.contains(code))
      incidents <- incidentsByStnCode(keys)
    } yield incidents


//try in the middle

//Or recover in the middle:

val sum = for {
  int1 <- Try(Integer.parseInt("one")).recover { case e => 0 }
  int2 <- Try(Integer.parseInt("two"))
} yield {
  int1 + int2
}
