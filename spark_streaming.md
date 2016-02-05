#### Setting up a driver that can recover from failure in Scala

```scala
def createStreamingContext() = {

val sc = new SparkContext(conf)
// Create a StreamingContext with a 1 second batch size
val ssc = new StreamingContext(sc, Seconds(1))
ssc.checkpoint(checkpointDir)
}

val ssc = StreamingContext.getOrCreate(checkpointDir, createStreamingContext _)
```
