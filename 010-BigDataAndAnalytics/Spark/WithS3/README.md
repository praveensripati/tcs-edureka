
# Integrating Spark with AWS S3

1. Create an IAM Role for EC2 with `AmazonS3FullAccess` policy and attach the same to the EC2.

1. Get the Hadoop jar file and extract it.

    ```
    wget https://archive.apache.org/dist/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
    tar -xvf hadoop-2.7.3.tar.gz
    ```
1. Copy the AWS/Hadoop jars to the Spark Classpath.

    ```
    cp ./hadoop-2.7.3/share/hadoop/tools/lib/hadoop-aws-2.7.3.jar ./spark-2.4.8-bin-hadoop2.7/jars/
    cp ./hadoop-2.7.3/share/hadoop/tools/lib/aws-java-sdk-1.7.4.jar ./spark-2.4.8-bin-hadoop2.7/jars/
    ```

1. Make sure there are some text files in S3.

1. Execute the below commands in the `spark-shell`. Make sure to use the proper S3 path.

    ```
    val novels = sc.textFile("s3a://praveen-gutenberg-novels/input/")
    val counts = novels.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
    counts.saveAsTextFile("s3a://praveen-gutenberg-novels/output/")
    ```

1. Verify the word count output in S3.
