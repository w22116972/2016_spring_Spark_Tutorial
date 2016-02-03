# 2016_spring_Spark_Tutorial

After downloaded Spark-1.6.0-bin-hadoop.2.6
Change directory to spark 
```bash
cd ~/spark-1.6.0-bin-hadoop2.6/
```

Use Spark shell
```bash
./bin/spark-shell
```

Spark-shell
```scala
// relative path is based on spark directory
val text = sc.textFile("README.md")
text.count()
text.filter(line => line.contains("Spark")).count()
text.first()
text.take(10)
text.collect()
```

Simple word count
```scala
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
```




