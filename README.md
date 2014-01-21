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
Use actors to safely encapsulate mutable state (maintains a  mono-threaded process)
