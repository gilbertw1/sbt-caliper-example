import sbt._
import Keys._

object BuildSettings {
    val buildOrganization = "org.example"
    val buildVersion      = "0.1"
    val buildScalaVersion = "2.10.1"

    val buildSettings = Defaults.defaultSettings ++ Seq (
        organization := buildOrganization,
        version      := buildVersion,
        scalaVersion := buildScalaVersion
    )
}

object Resolvers {
    val typesafeRepo = "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
}

object Dependencies {
    val akkaVersion = "2.1.1"

    val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
    val akkaRemote = "com.typesafe.akka" %% "akka-remote" % akkaVersion
    val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
    val akkaCluster = "com.typesafe.akka" %% "akka-cluster-experimental" % akkaVersion

    val googleCaliper = "com.google.caliper" % "caliper" % "0.5-rc1"
    val googleInstrumenter = "com.google.code.java-allocation-instrumenter" % "java-allocation-instrumenter" % "2.0"

    val akkaDependencies = Seq(akkaActor, akkaRemote, akkaSlf4j, akkaTestkit, akkaCluster)
    val allDependencies = akkaDependencies

    val benchmarkDependencies = Seq(googleCaliper, googleInstrumenter)
}

object ExampleProject extends Build {
    import Resolvers._
    import BuildSettings._
    import Defaults._

    lazy val exampleProject = 
        Project ("example-project", file("."))
            .settings ( buildSettings : _* )
            .settings ( resolvers ++= Seq(typesafeRepo) )
            .settings ( libraryDependencies ++= Dependencies.allDependencies )
            .settings ( scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature") )

    lazy val exampleBenchmark = 
        Project ("benchmark", file("benchmark"))
            .settings ( buildSettings : _* )
            .settings ( resolvers ++= Seq(typesafeRepo) )
            .settings ( libraryDependencies ++= Dependencies.allDependencies )
            .settings ( libraryDependencies ++= Dependencies.benchmarkDependencies )
            .settings ( fork in run := true )
            .settings ( javaOptions in run <++= (fullClasspath in Runtime) map { cp => Seq("-cp", sbt.Build.data(cp).mkString(":")) } )
            .dependsOn ( exampleProject )

}