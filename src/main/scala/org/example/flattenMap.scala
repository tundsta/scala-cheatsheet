val flattenMapValues = Map("key1" -> Some("value1"), "key2" -> None, "key3" -> Some("value3")).collect {
                                                                                  case (key, Some(value)) => key -> value }
