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
  "org.mongodb" % "mongo-java-driver" % "3.2.2",
 // "org.jongo" % "jongo" % "1.3.0"
  "uk.co.panaxiom" % "play-jongo_2.11" % "2.0.0-jongo1.3"

)



