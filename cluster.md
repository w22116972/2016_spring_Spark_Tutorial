**Cluster Managers** : application lauched on a set of machines using an external service.

**Driver** : process where the *main()* method of your program runs.
- converting a user program into tasks
- scheduling tasks on executors

**Executors** : worker processes responsible for running the individual tasks in a given Spark job.
- return results to the driver
- provide in-memory storage for RDDs that are cached by user programs

Spark depends on a cluster manager to launch executors.
1. The user submits an application using spark-submit.

2. spark-submit launches the driver program and invokes the main() method
specified by the user.
3. The driver program contacts the cluster manager to ask for resources to launch
executors.
4. The cluster manager launches executors on behalf of the driver program.
5. The driver process runs through the user application. Based on the RDD actions
and transformations in the program, the driver sends work to executors in the
form of tasks.
6. Tasks are run on executor processes to compute and save results.
7. If the driver’s main() method exits or it calls SparkContext.stop(), it will terminate
the executors and release resources from the cluster manager.

---

#### --master (Cluster URL)

spark://host:port
- Connect to a Spark Standalone cluster at the specified port. By default Spark Standalone masters use port 7077.

mesos://host:port
- Connect to a Mesos cluster master at the specified port. By default Mesos masters listen on port 5050.

yarn
- Connect to a YARN cluster. When running on YARN you’ll need to set the HADOOP_CONF_DIR environment variable to point the location of your Hadoop configuration directory, which contains information about the cluster.

local 
- Run in local mode with a single core.

local[N] 
- Run in local mode with N cores.

local[*] 
- Run in local mode and use as many cores as the machine has.

---

#### flag for spark-submit

--master
Indicates the cluster manager to connect to.

--class
The “main” class of your application if you’re running a Scala program.

--name
Name for your application in Spark’s web UI.

--files
Data files that you want to distribute to each node.

--executor-memory "512m"
--driver-memory "15g"

---

## Standalone
- consists of a master and multiple workers, each with a configured amount of memory and CPU cores

```bash
spark-submit --master spark://masternode:7077 yourapp
```

Approach in *sbin* directory (MAC or Linux)

1. Copy a compiled version of Spark to the same location on all your machines eg. /home/yourname/spark
2. Set up password-less SSH access from your master machine to the others
- requires the same user account on all the machines
``` bash
# creating a private SSH key for it on the master via ssh-keygen
# On master: run ssh-keygen accepting default options
$ ssh-keygen -t dsa
Enter file in which to save the key (/home/you/.ssh/id_dsa): [ENTER]
Enter passphrase (empty for no passphrase): [EMPTY]
Enter same passphrase again: [EMPTY]

# adding this key to the .ssh/authorized_keys file of all the workers
# On workers:
# copy ~/.ssh/id_dsa.pub from your master to the worker, then use:
$ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
$ chmod 644 ~/.ssh/authorized_keys
```
3. Edit the conf/slaves file on your master and fill in the workers’ hostnames
4. run *sbin/start-all.sh* on your master
5. To stop the cluster, run *bin/stop-all.sh* on your master node.

- cluster manager’s web UI http://masternode:8080



---

## Apache Mesos
- is a general-purpose cluster manager that can run both analytics workloads and long-running services

```bash
spark-submit --master mesos://masternode:5050 yourapp
```
