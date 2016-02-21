```bash
# Update the packages.
$ sudo apt-get update

# Install a few utility tools.
$ sudo apt-get install -y tar wget git

# Install the latest OpenJDK.
$ sudo apt-get install -y openjdk-7-jdk

# Install autotools (Only necessary if building from git repository).
$ sudo apt-get install -y autoconf libtool

# Install other Mesos dependencies.
$ sudo apt-get -y install build-essential python-dev python-boto libcurl4-nss-dev libsasl2-dev libsasl2-modules maven libapr1-dev libsvn-dev


# Install Command Line Tools.
$ xcode-select --install

# Install Homebrew.
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

# Install Java.
$ brew install Caskroom/cask/java

# Install libraries.
$ brew install wget git autoconf automake libtool subversion maven

# Change working directory.
$ cd mesos

# Bootstrap (Only required if building from git repository).
$ ./bootstrap

# Configure and build.
$ mkdir build
$ cd build
$ ../configure
$ make


# Change into build directory.
$ cd build

# Start mesos master (Ensure work directory exists and has proper permissions).
$ ./bin/mesos-master.sh --ip=127.0.0.1 --work_dir=/var/lib/mesos

# Start mesos slave.
$ ./bin/mesos-slave.sh --master=127.0.0.1:5050

# Visit the mesos web page.
$ http://127.0.0.1:5050

# Run C++ framework (Exits after successfully running some tasks.).
$ ./src/test-framework --master=127.0.0.1:5050

# Run Java framework (Exits after successfully running some tasks.).
$ ./src/examples/java/test-framework 127.0.0.1:5050

# Run Python framework (Exits after successfully running some tasks.).
$ ./src/examples/python/test-framework 127.0.0.1:5050



```
