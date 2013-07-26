import com.typesafe.sbtscalariform.ScalariformPlugin._
import sbt._
import sbt.Keys._
import sbt.ScriptedPlugin._
import sbtrelease.ReleasePlugin._
import adept.sbt.AdeptKeys._
import adept.sbt.AdeptPlugin._


object Build extends Build {

  lazy val root = Project(
    "sbteclipse",
    file("."),
    aggregate = Seq(sbteclipseCore, sbteclipsePlugin),
    settings = commonSettings ++ Seq(
      publishArtifact := false

    )
  )

  lazy val sbteclipseCore = Project(
    "sbteclipse-core",
    file("sbteclipse-core"),
    settings = commonSettings ++ Seq(
      adeptArtifactLocations := Map(
        "jar" -> "https://typesafe.artifactoryonline.com/typesafe/ivy-releases/com.typesafe.sbteclipse/sbteclipse-core/scala_2.9.2/sbt_0.12/2.1.1/jars/sbteclipse-core.jar"
      ),
      adeptDependencies ++= Seq("org.scalaz" %% "scalaz-core" % "6.0.4")
    )
  )

  lazy val sbteclipsePlugin = Project(
    "sbteclipse-plugin",
    file("sbteclipse-plugin"),
    dependencies = Seq(sbteclipseCore % "compile"),
    settings = commonSettings ++ Seq(
      adeptArtifactLocations := Map(
        "jar" -> "https://typesafe.artifactoryonline.com/typesafe/ivy-releases/com.typesafe.sbteclipse/sbteclipse-plugin/scala_2.9.2/sbt_0.12/2.1.1/jars/sbteclipse-plugin.jar"
      )
    )
  )

  def commonSettings = Defaults.defaultSettings ++
    scalariformSettings ++
    scriptedSettings ++
    releaseSettings ++
    adeptSettings ++
    Seq(
      organization := "com.typesafe.sbteclipse",
      // version is defined in version.sbt in order to support sbt-release
      scalacOptions ++= Seq("-unchecked", "-deprecation"),
      publishTo <<= isSnapshot { isSnapshot =>
        val id = if (isSnapshot) "snapshots" else "releases"
        val uri = "https://typesafe.artifactoryonline.com/typesafe/ivy-" + id
        Some(Resolver.url("typesafe-" + id, url(uri))(Resolver.ivyStylePatterns))
      },
      sbtPlugin := true,
      publishMavenStyle := false,
      publishArtifact in (Compile, packageDoc) := false,
      publishArtifact in (Compile, packageSrc) := false,
      scriptedLaunchOpts += "-Xmx1024m",
      adeptRepositories += "central" -> "git@github.com:freekh/adept-central.git"
    )
} 
