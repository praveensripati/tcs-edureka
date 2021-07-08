name := "scalaprogs"
version :="0.0.1"
scalaVersion := "2.11.12"

val sparkVersion = "2.4.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-graphx" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
)