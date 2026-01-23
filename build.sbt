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
    "scm:git@github.com:spice-labs-inc/rodeo-components.git"
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

val _mavenCentral = "maven-central"
val _isMavenCentralPublish = sys.env.getOrElse("PUBLISHING_DESTINATION", _mavenCentral) == _mavenCentral
val repo = "https://maven.pkg.github.com/spice-labs-inc/rodeo-components"
val githubResolver = Some("GitHub Package Registry" at repo)

ThisBuild / publishTo := {
  val log = sLog.value
  if (_isMavenCentralPublish) {
    log.info("setting publishTo to localStaging")
    localStaging.value
  } else {
    log.info("setting publishTo to githubResolver")
    githubResolver
  }
}

credentials += Credentials(
  "GitHub Package Registry",
  "maven.pkg.github.com",
  "x-access-token",
  sys.env.getOrElse("GITHUB_TOKEN", "")
)

// make the PGP_PASSPHRASE available
ThisBuild / pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toCharArray)
Global / excludeLintKeys += pgpPassphrase

Compile / packageBin := (Compile /  packageBin).value

publishMavenStyle := true


lazy val root = project
  .in(file("."))
  .settings(
    name := "rodeo-components",

    organization := "io.spicelabs",

    version := version.value,

    scalaVersion := scala3Version,

    libraryDependencies += "org.jetbrains" % "annotations" % "26.0.2-1",
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
