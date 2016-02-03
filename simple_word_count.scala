import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object test {
  def  main (args: Array[String]){
    System.setProperty("hadoop.home.dir", "E:\\hadoop-common-2.2.0-bin-master")
    val conf = new SparkConf().setAppName("test_mvn").setMaster("local")
    val sc = new SparkContext(conf)

    val txt = sc.textFile("E:\\spark-1.6.0-bin-hadoop2.6\\README.md")
    val txt_split = txt.flatMap(_.split(" ")) // (line => line.split(" "))
    val txt_map = txt_split.map((_, 1))       // (line => (line, 1))
    val txt_red = txt_map.reduceByKey(_ + _)  // ((a, b) => a + b)
    
    val wordCount = txt.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    
    wordCount.foreach(println) // rdd.map(println)
  }
}
