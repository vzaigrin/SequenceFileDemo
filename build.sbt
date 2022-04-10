ThisBuild / version := "1.0"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "3.3.1",
  "ch.qos.logback" % "logback-classic" % "1.2.11"
)

lazy val write = (project in file("write"))
  .settings(assembly / mainClass := Some("ru.example.sequencefile.WriteDemo"))
  .settings(assembly / assemblyJarName := "write.jar")
  .settings(assembly / assemblyMergeStrategy := {
    case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
    case m if m.toLowerCase.endsWith("meta-inf")    => MergeStrategy.discard
    case "module-info.class"                        => MergeStrategy.first
    case _                                          => MergeStrategy.first
  })

lazy val read = (project in file("read"))
  .settings(assembly / mainClass := Some("ru.example.sequencefile.ReadDemo"))
  .settings(assembly / assemblyJarName := "read.jar")
  .settings(assembly / assemblyMergeStrategy := {
    case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
    case m if m.toLowerCase.endsWith("meta-inf")    => MergeStrategy.discard
    case "module-info.class"                        => MergeStrategy.first
    case _                                          => MergeStrategy.first
  })

assembly / assemblyMergeStrategy := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.toLowerCase.endsWith("meta-inf")    => MergeStrategy.discard
  case "module-info.class"                        => MergeStrategy.first
  case _                                          => MergeStrategy.first
}
