scala-cheatsheet
================

Reference for Scala syntax, idioms and general good practice

Conditional Structures
======================

When we use languages with functional features, we can do most of the work that we do in loops using maps, filters, and folds. 
Control structures end up disappearing, and that can be a good thing.

Side Effect Functions
=======================
Decomposing logic into 'pure' and side effects, these impure functions form an 'imperative shell' around the pure core of the program.

Actors
=====================
Use actors to safely encapsulate mutable state, concurrency and fault tolerance strategies


```scala
//concatenate elements to a reactive mongo BSONDocument
 Seq(
  article.creationDate map (cd => "creationDate" -> BSONDateTime(cd.getMillis)),
  article.updateDate map (ud => "updateDate" -> BSONDateTime(ud.getMillis))
).flatten.foldLeft(bson) ((a,b)=> a ++ b)
    }
```

Pattern matching/extracting nested case classes
===============================================
```scala
 val foodEvent: PartialFunction[ Food, Option[  FOOD_EVENT ] ] = {
     case Food(ingredient @ (_: Raisin | _:Cherry)) => Some(FOOD_EVENT(ingredient))
   }
 ```