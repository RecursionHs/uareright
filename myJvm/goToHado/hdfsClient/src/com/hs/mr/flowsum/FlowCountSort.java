package com.hs.mr.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCountSort {

    static class   FlowCountSortMapper extends Mapper<LongWritable,Text,FlowBean,Text>{
        FlowBean bean = new FlowBean();
        Text v = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");
            String phoneNo = splits[0];
            Long upFlow = Long.parseLong(splits[1]);
            Long dFlow = Long.parseLong(splits[2]);

            bean.set(upFlow,dFlow);
            v.set(phoneNo);

            context.write(bean,v);


        }
    }
    static class FlowCountSortReducer extends Reducer<FlowBean,Text,Text,FlowBean>{
        @Override
        protected void reduce(FlowBean bean, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(values.iterator().next(),bean);
        }
    }

    public static void main(String[] args) throws Exception{
        //配置文件
        Configuration config = new Configuration();

        //job对象
        Job job = Job.getInstance(config);

        //指定jar包所在路径
        job.setJarByClass(FlowCountSort.class);

        //指定本业务job要使用的mapper/reducer业务类
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);

        //指定mapper输出的KV类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        //指定最终数据的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //指定job输入原始文件所在位置
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //指定job输出的结果所在目录
        Path outPath = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(config);
        if(fileSystem.exists(outPath)){
            fileSystem.delete(outPath,true);
        }
        FileOutputFormat.setOutputPath(job,outPath);

        //将job中配置的参数交给yarn去运行
        /*job.submit();*/

        boolean runStatus = job.waitForCompletion(true);
        System.exit(runStatus ? 0:1);

    }
}
