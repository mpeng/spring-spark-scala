package com.magellen 
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkWordCount {
  def main(args: Array[String]) {
    // create Spark context with Spark configuration
    val sc = new SparkContext(new SparkConf().setAppName("Spark Count"))

    // get threshold
    val threshold = args(1).toInt

    // read in text file and split each document into words
    val tokenized = sc.textFile(args(0)).flatMap(_.split(" "))

    // count the occurrence of each word
    val wordCounts = tokenized.map((_, 1)).reduceByKey(_ + _)

    // filter out words with fewer than threshold occurrences
    val filtered = wordCounts.filter(_._2 >= threshold)

    // count characters
    val charCounts = filtered.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)

	System.out.println( "=================WELCOME1================" );
    System.out.println(charCounts.collect().mkString(", "))
	System.out.println( "=================WELCOME2================" );
    System.out.println(wordCounts)
	System.out.println( "=================WELCOME3================" );
    System.out.println(filtered)
	System.out.println( "=================WELCOME4================" );
    System.out.println(charCounts)
	System.out.println( "=================WELCOME5================" );
  }
}
