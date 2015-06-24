lazy val commonSettings = Seq(
  name := "lift-examples",
  version := "1.0-snapshot",
  scalaVersion := "2.11.6"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"
)

seq(webSettings: _*)

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

unmanagedSourceDirectories in Compile <<= baseDirectory { base =>
  Seq(
    base / "src/main/scala"
  )
}

unmanagedSourceDirectories in Test <<= baseDirectory { base =>
  Seq(
    base / "src/main/webapp",
    base / "src/test/scala"
  )
}

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked"
)

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile",
    "net.liftmodules" %% "lift-jquery-module_2.6" % "2.8",
    "org.eclipse.jetty" % "jetty-webapp" % "9.3.0.v20150612" % "container,test",
    "org.eclipse.jetty" % "jetty-plus" % "9.3.0.v20150612" % "container,test", // For Jetty Config
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "org.specs2" %% "specs2" % "2.3.12" % "test",
    "com.h2database" % "h2" % "1.4.187"
  )
}

