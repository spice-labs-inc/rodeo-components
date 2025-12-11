val projectName = "rodeo-components"
val scala3Version = "3.7.3"
val _homepage = Some(url("https://github.com/spice-labs-inc/rodeo-components"))

ThisBuild / organization := "io.spicelabs"
ThisBuild / organizationName := "Spice Labs"
ThisBuild / organizationHomepage := _homepage
ThisBuild / version := "0.0.1-SNAPSHOT" // overriden by GitHub actions
ThisBuild / description := "A library for extending Goat Rodeo with add-on components"
ThisBuild / licenses := Seq(
  "Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")
)

// This is the Source Code Management info for maven
ThisBuild / homepage := _homepage
ThisBuild / scmInfo := Some (
  ScmInfo(
    url("https://github.com/spice-labs-inc/rodeo-components"),
    "scm:git@github.com:spice-labs-inc/cilantro.git"
  )
)

// This is the developer information
ThisBuild / developers := List(
  Developer(
    id = "spicelabs",
    name = "Spice Labs",
    email = "engineering@spicelabs.io",
    url = url("https://github.com/spice-labs-inc")
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "goat-components",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
