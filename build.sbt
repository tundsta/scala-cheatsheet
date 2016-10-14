name := """scala-cheatsheet"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.10"

libraryDependencies += "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.10"

libraryDependencies += "com.typesafe.play" % "play-ws_2.11" % "2.5.8"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.14.0"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"



