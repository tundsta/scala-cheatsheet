//using collect to filter a collection
val results3 =  elements collect { case se: SpecialElement if accept(se) => transform(se) }