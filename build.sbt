

val scalaCompilerOptions = Seq(
  "-encoding", "UTF-8",
  "-deprecation", // warning and location for usages of deprecated APIs
  "-feature", // warning and location for usages of features that should be imported explicitly
  "-unchecked", // additional warnings where generated code depends on assumptions
  "-Xlint:_", // recommended additional warnings
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-unused-import", // Warn when imports are unused
  "-Ywarn-unused", // Warn when local and private vals, vars, defs, and types are unused
  "-Ywarn-numeric-widen", // Warn when numerics are widened, Int and Double, for instance
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-dead-code", // Warn when dead code is identified
  "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`
  "-Ywarn-nullary-override", //  Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit", // Warn when nullary methods return Unit
  "-language:reflectiveCalls",
  "-language:postfixOps" // too lazy?
)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.allixender",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT",
      scalacOptions ++= scalaCompilerOptions
    )),
    name := "scala-mockserver-setup",
    resolvers += Resolver.bintrayIvyRepo("allixender", "ivy2"),
    libraryDependencies ++= Seq(
      "org.mock-server" % "mockserver-netty" % "3.11",
      "com.github.unisay" %% "mockserver-client-scala" % "0.2.1",
      "org.scalatest" %% "scalatest" % "3.0.3" % Test
    )
  )
