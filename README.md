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

####How to use spark-submit with IntelliJ IDEA

1. click "Terminal" on the bottom side
2. ``` sbt package```
3. now you have a .jar in your project\target\scala-2.10\YOURPROJECT_2.10-1.0.jar
4. ``` cd SPARK_DIRECTORY```
5. ``` .\bin\spark-submit --name "test" --master local \...\YOURPROJECT_2.10-1.0.jar ```

---

##Troubleshooting
*java.lang.IllegalArgumentException: System memory 259522560 must be at least 4.718592E8. Please use a larger heap size.*

1. "Run" -> "Edit Configuration" -> "Application"
2. Main class: YOUR_MAIN_OBJECT
3. VM options: -Xmx512m

*ERROR Shell: Failed to locate the winutils binary in the hadoop binary path*
*java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.*

1. https://github.com/srccodes/hadoop-common-2.2.0-bin/tree/master/bin
2. ``` System.setProperty("hadoop.home.dir", "\...\hadoop-common-2.2.0-bin-master")``` in Main function


