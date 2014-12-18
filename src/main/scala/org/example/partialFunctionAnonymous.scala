val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
  ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil
  
val predicate: ((String, Int)) => Boolean = { case (_, f) => f > 3 && f < 25 }
val transform: ((String, Int)) => String = { case (w, _) => w }
wordFrequencies.filter(predicate).map(transform)