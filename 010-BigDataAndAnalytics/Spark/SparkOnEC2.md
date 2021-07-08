
# Spark on AWS EC2

Spark can be run via EMR, but it takes about 15 minutes to get started with EMR and is also not free. So, here we explore on how get started with Spark on EC2.

1. Create an Ubuntu EC2 (t2.medium or bigger) and connect to it.

1. Install the required prerequisites.

    ```
    echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
    echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add

    sudo apt-get update
    sudo apt install openjdk-8-jdk scala python python-pip sbt -y
    pip install numpy py4j
    ```
1. Download the install Spark on the EC2. The latest Spark can be downloaded from [this](https://spark.apache.org/downloads.html) URL.

    ```
    wget https://mirrors.estointernet.in/apache/spark/spark-2.4.8/spark-2.4.8-bin-hadoop2.7.tgz
    tar -zxvf spark-2.4.8-bin-hadoop2.7.tgz
    ```

1. Add the below exports to the `.bashrc` file. And run the `source .bashrc` for this to take place into effect.

    ```
    export SPARK_HOME=/home/ubuntu/spark-2.4.8-bin-hadoop2.7
    export PYSPARK_PYTHON=python
    export PYTHONPATH=$SPARK_HOME/python:$PYTHONPATH
    export PATH=$SPARK_HOME/bin:$PATH
    ```
1. Disable the INFO logging as it spits a lot of messages. Change INFO to WARN in the below file.

    ```
    cd $SPARK_HOME/conf
    cp log4j.properties.template log4j.properties
    #Make the changes to the file
    cd
    ```
