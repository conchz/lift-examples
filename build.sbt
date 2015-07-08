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
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftweb" %% "lift-mapper" % liftVersion,
    "net.liftweb" %% "lift-squeryl-record" % liftVersion,
    "net.liftmodules" %% "lift-jquery-module_2.6" % "2.8",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
    "org.eclipse.jetty" % "jetty-webapp" % "9.3.0.v20150612" % "container,test",
    "org.eclipse.jetty" % "jetty-plus" % "9.3.0.v20150612" % "container,test", // For Jetty Config
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.json4s" %% "json4s-native" % "3.2.11",
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "com.zaxxer" % "HikariCP" % "2.3.8",
    "mysql" % "mysql-connector-java" % "5.1.36",
    "org.javassist" % "javassist" % "3.20.0-GA",
    "org.slf4j" % "slf4j-log4j12" % "1.7.12",
    "org.specs2" %% "specs2" % "2.3.13" % "test"
  )
}

webSettings

env in Compile := Some(file("./src/main/webapp/WEB-INF/jetty-env.xml").asFile)
port in container.Configuration := 8081

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}
