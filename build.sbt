name := "Paper Football"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaVersion = "2.4.0"
  Seq(
    "ch.qos.logback"       %  "logback-classic" % "1.1.3",
    "org.scalafx"         %%  "scalafx"         % "8.0.0-R4",
    "com.typesafe.akka"   %%  "akka-actor"      % akkaVersion,
    "com.typesafe.akka"   %%  "akka-testkit"    % akkaVersion   % "test",
    "org.scalatest"        %  "scalatest_2.11"  % "2.2.4" % "test",
    "org.specs2"          %%  "specs2-core"     % "2.3.11" % "test"
  )
}
