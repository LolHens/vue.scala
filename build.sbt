inThisBuild(Seq(
  name := "vue.scala",
  organization := "org.lolhens",
  version := "0.0.0",

  scalaVersion := "2.12.2",

  externalResolvers := Seq("artifactory" at "http://lolhens.no-ip.org/artifactory/maven-public/"),

  dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254"),

  mainClass in Compile := None
))

name := (name in ThisBuild).value

lazy val settings = Seq(
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")
)

lazy val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(publishArtifact := false)
  .aggregate(vueScalatags, vueScalajs)

lazy val vueScalatags = project.in(file("vue-scalatags"))
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats" % "0.9.0",
      "com.lihaoyi" %% "scalatags" % "0.6.5"
    )
  )
  .settings(settings: _*)

lazy val vueScalajs = crossProject.crossType(CrossType.Full).in(file("vue-scalajs"))
  .settings(name := (name in ThisBuild).value)
  .settings(settings: _*)
  .jsSettings(jsDependencies ++= Seq(
    "org.webjars.npm" % "vue" % "2.3.3" / "2.3.3/vue.js"
  ))
  .js
