resolvers ++= Seq(
  Classpaths.sbtPluginReleases,
  Resolver.jcenterRepo,
  "Typesafe Repo"           at "http://repo.typesafe.com/typesafe/releases/",
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases",
  Resolver.bintrayRepo("unisay", "maven")
)

// https://github.com/sbt/sbt-bintray/issues/104
// addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.4.0")
// addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")