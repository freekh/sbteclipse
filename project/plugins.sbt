
libraryDependencies <+= (sbtVersion)("org.scala-sbt" % "scripted-plugin" % _)

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.5")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.5.1")


//adept:

resolvers += Resolver.url("bintray", new URL("http://dl.bintray.com/freekh/adept-ivy"))(Resolver.ivyStylePatterns)

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "spray repo" at "http://repo.spray.io"

addSbtPlugin("org.adept" % "adept-sbt" % "0.8.0-ALPHA-20130812155932")

