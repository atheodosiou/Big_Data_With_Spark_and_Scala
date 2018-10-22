import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object SparkScala {
  def main(args: Array[String]): Unit = {
    println("Starting Spark Project...")

    //Use to set the loggig levet to ERROR
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]","RatingsCounter")

    println("Reading data...")

    //Load up each line of the ratings data into an RDD
    val lines = sc.textFile("src/Data/ml-100k/u.data")

    val ratings = lines.map(x=>x.toString().split("\t")(2))

//    val movies =lines.map(x=>x.toString().split("\t")(1))
//    val users = lines.map(x=>x.toString().split("\t")(0))
//
//    val totalUsers=users.distinct().count()
//    users.distinct().foreach(println)
//    val usrRatings=users.countByValue();
//    val usrResults=usrRatings.toSeq.sortBy(_._1)
//    usrResults.foreach(println)
//    val totalMovies=movies.distinct().count()
//
//    println("Number of total users: "+totalUsers)
//    println("Number of total movies: "+totalMovies)

    val results = ratings.countByValue()

    val sortedResults = results.toSeq.sortBy(_._1)

    sortedResults.foreach(println)

  }
}
