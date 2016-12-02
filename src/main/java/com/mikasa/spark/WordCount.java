package com.mikasa.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by sherlock on 2016/11/24.
 */
public class WordCount {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Spark WordCount written by Java");
        conf.setMaster("local");

        //如果不同的语言具体的类名称不同，如果是Java的话，则为JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf); //其底层实际上就是Scala的SparkContext

        JavaRDD<String> lines = sc.textFile("G:\\runtime\\spark-1.6.0\\README.md");

        //Java8  lambda
        JavaRDD<String> words = lines.flatMap(line->{return Arrays.asList(line.split(" "));} );
        //Java原始写法
        //如果是Scala的话，由于SAM转换，所以可以写成一行代码
        /*
        lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" "));
            }
        });
        */
        JavaPairRDD<String,Integer> pairs = words.mapToPair(word->{return new Tuple2<String, Integer>(word, 1);});

        JavaPairRDD<String,Integer> wordsCount = pairs.reduceByKey((Integer a,Integer b)->a+b);

        //排序
        wordsCount = wordsCount.mapToPair(tuple->new Tuple2(tuple._2,tuple._1));
        wordsCount = wordsCount.sortByKey(false);
        wordsCount = wordsCount.mapToPair(tuple->new Tuple2(tuple._2,tuple._1));

        wordsCount.foreach(tuple->{
            System.out.println(tuple._1+":"+tuple._2);
        });

        sc.stop();
    }
}
