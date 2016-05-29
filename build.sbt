name := """BEABAZ_REST"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs)

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.3",
  "org.mongojack" % "mongojack" % "2.6.0",
  "org.jongo" % "jongo" % "1.3.0"

)



