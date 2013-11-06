import sbt._
import sbt.Keys._

object ScalacheatsheetBuild extends Build {

  lazy val scalacheatsheet = Project(
    id = "scala-cheatsheet",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "scala-cheatsheet",
      organization := "org.example",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.3"
      // add other settings here
    )
  )
}
