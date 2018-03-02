# 2016_spring_Spark_Tutorial

## Use Spark shell

**MAC ver.**

1.  ``` cd ~/.../spark-1.6.0-bin-hadoop2.6 ```
2.  ``` ./bin/spark-shell ```

**Windows ver.**

1. ``` cd ~\...\spark-1.6.0-bin-hadoop2.6 ```
2. ``` .\bin\spark-shell ```

Spark-shell
```scala
// relative path is based on spark directory
/** each line in this text file is a new element of a single RDD */
val text = sc.textFile("README.md")
/** count the number of elements in this RDD */
text.count() 
/** count the number of elements which contain keyword "Spark" */
text.filter(line => line.contains("Spark")).count()
/** find first element in this RDD */
text.first()
/** list top 10 elements in this RDD */
text.take(10)
/** list all of elements in this RDD */
text.collect()
```

---

#### How to use spark-submit with IntelliJ IDEA

1. click "Terminal" on the bottom side
2. ``` sbt package```
3. now you have a .jar in your project\target\scala-2.10\YOURPROJECT_2.10-1.0.jar
4. ``` cd SPARK_DIRECTORY```
5. MAC: ``` ./bin/spark_submit --name "test" --master local /.../YOURPROJECT_2.10-1.0.jar```
6. WIN: ``` .\bin\spark-submit --name "test" --master local \...\YOURPROJECT_2.10-1.0.jar ```

---

## Troubleshooting
```java.lang.IllegalArgumentException: System memory 259522560 must be at least 4.718592E8. Please use a larger heap size.```

1. "Run" -> "Edit Configuration" -> "Application"
2. Main class: YOUR_MAIN_OBJECT
3. VM options: -Xmx512m

```ERROR Shell: Failed to locate the winutils binary in the hadoop binary path```
```java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.```

1. https://github.com/NCTUee104/hadoop-common-2.2.0-bin
2. ``` System.setProperty("hadoop.home.dir", "\...\hadoop-common-2.2.0-bin-master")``` in Main function


