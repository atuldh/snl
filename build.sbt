name := """snakesnladder"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"


libraryDependencies += ws
libraryDependencies += cache
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "1.9.0"
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.12.0"
libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "org.typelevel" %% "cats-core" % "0.9.0"

