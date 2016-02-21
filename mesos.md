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
```
