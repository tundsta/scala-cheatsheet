
 val foodEvent: PartialFunction[ Food, Option[  FOOD_EVENT ] ] = {
     case Food(ingredient @ (_: Raisin | _:Cherry)) => Some(FOOD_EVENT(ingredient))
   }
 
