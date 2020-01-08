package com.hs.mr.wcdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

    public static void main(String[] args) throws Exception{
        
        //配置文件
        Configuration config = new Configuration();

        //job对象
        Job job = Job.getInstance(config);

        //指定jar包所在路径
        job.setJarByClass(WordCountDriver.class);

        //指定本业务job要使用的mapper/reducer业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //指定mapper输出的KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定最终数据的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定job输入原始文件所在位置
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //指定job输出的结果所在目录
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //将job中配置的参数交给yarn去运行
        /*job.submit();*/

        boolean runStatus = job.waitForCompletion(true);
        System.exit(runStatus ? 0:1);

    }
}
