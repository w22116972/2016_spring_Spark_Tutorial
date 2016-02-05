- Each receiver runs as a long-running task within Spark’s executors, and hence occupies CPU cores allocated to the application

## Kafka
- Apache Kafka is publish-subscribe messaging rethought as a distributed, partitioned, replicated commit log service

```
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.0"
```

#### Direct Approach (No Receivers)
- Simplified Parallelism: No need to create multiple input Kafka streams and union them.
- Spark Streaming will create as many RDD partitions as there are Kafka partitions to consume, which will all read data from Kafka in parallel. There is a one-to-one mapping between Kafka and RDD partitions
- Exactly-once semantics

```scala
 import org.apache.spark.streaming.kafka._
 val directKafkaStream = KafkaUtils.createDirectStream[
     [key class], [value class], [key decoder class], [value decoder class] ](
     streamingContext, [map of Kafka parameters], [set of topics to consume])
     
  /** access the Kafka offsets consumed in each batch. */
  // Hold a reference to the current offset ranges, so it can be used downstream
 var offsetRanges = Array[OffsetRange]()
 directKafkaStream.transform { rdd =>
   offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
   rdd
 }.map {
 }.foreachRDD { rdd =>
   for (o <- offsetRanges) {
     println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
   }
 }
```

---

#### Setting up a driver that can recover from failure in Scala
Spark Streaming can recompute state using the lineage graph of transformations, but checkpointing controls how far back it
must go.

```scala
def createStreamingContext() = {
val sc = new SparkContext(conf)
// Create a StreamingContext with a 1 second batch size
val ssc = new StreamingContext(sc, Seconds(1))
val line = ssc.socketTextStream("hdfs://...//")
ssc.checkpoint(checkpointDir)
ssc
}
val context = StreamingContext.getOrCreate(checkpointDir, createStreamingContext _)
context.start()
context.awaitTermination()
```

---

#### Writing data to external system requires creating a connection object

```scala
/** create a single connection object and send all the records in a RDD partition using that connection */
dstream.foreachRDD { rdd =>
  rdd.foreachPartition { partitionOfRecords =>
    val connection = createNewConnection()
    partitionOfRecords.foreach(record => connection.send(record))
    connection.close()
  }
}
```

```scala
/** Reusing connection objects across multiple RDDs/batches 
    maintain a static pool of connection objects */
dstream.foreachRDD { rdd =>
  rdd.foreachPartition { partitionOfRecords =>
    // ConnectionPool is a static, lazily initialized pool of connections
    val connection = ConnectionPool.getConnection()
    partitionOfRecords.foreach(record => connection.send(record))
    ConnectionPool.returnConnection(connection)  // return to the pool for future reuse
  }
}
```
