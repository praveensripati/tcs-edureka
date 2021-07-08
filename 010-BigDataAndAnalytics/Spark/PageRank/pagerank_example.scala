
package org.apache.spark.examples.graphx

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object pagerank_example {
  def main(args: Array[String]): Unit = {

    // Creates a SparkSession.
    val spark = SparkSession
      .builder
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()
    val sc = spark.sparkContext

    // Load the edges as a graph
    val graph = GraphLoader.edgeListFile(sc, "followers.txt")

    // Run PageRank
    val ranks = graph.pageRank(0.0001).vertices

    // Join the ranks with the usernames
    val users = sc.textFile("users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    // Print the result
    println(ranksByUsername.collect().mkString("\n"))

    spark.stop()
  }
}
