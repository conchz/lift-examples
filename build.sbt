name := "lift-examples"

version := "1.0-snapshot"

organization := "com.github.dolphineor"

scalaVersion := "2.11.7"

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "maven central repository" at "https://repo1.maven.org/maven2/",
  "typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  "sonatype repository" at "https://oss.sonatype.org/content/repositories/releases/"
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
    "org.json4s" %% "json4s-native" % "3.2.11",
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "com.typesafe.slick" %% "slick" % "3.0.0",
    "org.specs2" %% "specs2" % "3.3.1" % "test"
  )
}

webSettings

port in container.Configuration := 8081

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
