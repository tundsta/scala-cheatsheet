val m1 = Map(1 -> "a", 3 -> "c", 5 -> "e")

val overrideValueInImmutableMap = m1 + (1 -> "z")

val addValueToimmutableMap = m1 + (7->"y")

val filterMapByKeys = m1 filterKeys(Set(1,3))


