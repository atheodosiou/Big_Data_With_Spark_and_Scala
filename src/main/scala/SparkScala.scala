import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object SparkScala {
  def main(args: Array[String]): Unit = {
    println("Starting Spark Project...")

    //Use to set the loggig levet to ERROR
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]","Spark Tutorial")

    //Load up each line of the ratings data into an RDD
    val lines = sc.textFile("src/Data/ml-100k/u.data")

    val ratings = lines.map(x=>x.toString().split("\t")(2))

    val results = ratings.countByValue()

    val sortedResults = results.toSeq.sortBy(_._1)

    sortedResults.foreach(println)

  }
}
