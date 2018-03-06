name := "db-server"

version := "0.1"

scalaVersion := "2.12.4"


libraryDependencies ++= {
  val akkaV       = "2.5.9"
  val akkaHttpV   = "10.1.0-RC2"
  val scalaTestV  = "3.0.5"
  val slickV      = "3.2.0"
  val postgresV   = "42.0.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "com.typesafe.slick" %% "slick" % slickV,
    "org.postgresql" % "postgresql" % postgresV,
    "com.typesafe.slick" %% "slick-hikaricp" % slickV,
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
  )
}

dockerBaseImage := "openjdk:jre-alpine"

enablePlugins(AshScriptPlugin)
enablePlugins(JavaAppPackaging)