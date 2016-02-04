## textFile

```scala
/** each input line becomes an element in the RDD. */
val line = sc.textFile("file://.../README.md")
/** each text file becomes an element in the RDD.*/
// if each file represents a certain time period’s data, it will be useful.
val txt_files = sc.wholeTextFiles("file://.../DIRECTORY")
/** saving as a text file*/
result.saveAsTextFile("OUTPUT_DIRECTORY")
```

---

## CSV

SBT dependency :
```
libraryDependencies += "com.opencsv" % "opencsv" % "3.7"
```

```scala
import Java.io.StringReader
import au.com.bytecode.opencsv.CSVReader
val input = sc.textFile(inputFile)
val result = input.map{ line =>
  val reader = new CSVReader(new StringReader(line));
  reader.readNext();
}
```

```scala
/** we don’t output the field name with each record, 
to have a consistent output we need to create a mapping */
pandaLovers.map(person => List(person.name, person.favoriteAnimal).toArray)
.mapPartitions{people =>
  val stringWriter = new StringWriter();
  val csvWriter = new CSVWriter(stringWriter);
  csvWriter.writeAll(people.toList)
  Iterator(stringWriter.toString)
}.saveAsTextFile("OUTPUT_DIRECTORY")
```

---

## SequenceFile (Hadoop Format)

```scala
/** sequenceFile(path, keyClass, valueClass, minPartitions) */
val data = sc.sequenceFile(inFilePath, classOf[Text], classOf[IntWritable]).map{case (x, y) => (x.toString, y.get())}
data.saveAsSequenceFile("OUTPUT_DIRECTORY")
```

## Cassandra

SBT dependency :
```
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0-RC1"
```

```scala
/** point to our Cassandra cluster */
val conf = new SparkConf(true).set("spark.cassandra.connection.host", "hostname")
val sc = new SparkContext(conf)
```
Cassandra connector reads a job property to determine which cluster to connect to.

If usrName + pwd => *spark.cassandra.auth.username*,  *spark.cassandra.auth.password*
