name := "some-gatling-scenariose"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"

val gatlingDependencies = Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test",
  "io.gatling" % "gatling-test-framework" % "2.2.0" % "test"
)

lazy val root = project.in(file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    libraryDependencies ++= gatlingDependencies
  )