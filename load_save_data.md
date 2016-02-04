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

```scala
import com.datastax.spark.connector._
// Read entire table as an RDD. Assumes your table test was created as
// CREATE TABLE test.kv(key text PRIMARY KEY, value int);
val data = sc.cassandraTable("test" , "kv")
//or sc.cassandraTable(…).where("key=?", "panda")
// Print some basic stats on the value field.
data.map(row => row.getInt("value")).stats()

/** saving to Cassandra*/
val rdd = sc.parallelize(List(Seq("moremagic", 1)))
rdd.saveToCassandra("test" , "kv", SomeColumns("key", "value"))
```

---

## HBase

```scala
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
val conf = HBaseConfiguration.create()
conf.set(TableInputFormat.INPUT_TABLE, "tablename") // which table to scan
val rdd = sc.newAPIHadoopRDD(
conf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
```
