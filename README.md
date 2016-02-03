# 2016_spring_Spark_Tutorial

####Use Spark shell

1. After downloaded Spark-1.6.0-bin-hadoop.2.6
2.  ``` cd ~/spark-1.6.0-bin-hadoop2.6/ ```
3.  ``` ./bin/spark-shell ```


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

---

####Simple word count
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
---
####How to use spark-submit with IntelliJ IDEA

1. click "Terminal" on the bottom side
2. ``` sbt package```
3. now you have a .jar in your project\target\scala-2.10\YOURPROJECT_2.10-1.0.jar
4. ``` cd SPARK_DIRECTORY```
5. ``` .\bin\spark-submit --name "test" --master local \...\YOURPROJECT_2.10-1.0.jar ```

---



