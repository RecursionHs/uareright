package com.hs.mr.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {

        static  class FlowCountMapper extends Mapper<LongWritable,Text,Text,FlowBean>{
            @Override
            protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
                //读每行数据，取出手机号、上下行流量。
                String line = value.toString();
                String[] values = line.split("\t");
                //手机号
                String phoneNum = values[1];

                //上下行流量
                Long upFlow = Long.parseLong(values[values.length-3]);
                Long dFlow = Long.parseLong(values[values.length-2]);

                context.write(new Text(phoneNum),new FlowBean(upFlow,dFlow));
            }
        }

        static class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean>{
            @Override
            protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
                long  sum_upFlow = 0;
                long  sum_dFlow = 0;

                //遍历所有的bean，将其中的上行流量，
                //  下行流量分别累加
                for(FlowBean flow : values){
                    sum_upFlow += flow.getUpFlow();
                    sum_dFlow += flow.getdFlow();
                }

                FlowBean flowBean = new FlowBean(sum_upFlow,sum_dFlow);
                context.write(key,flowBean);
            }
        }

    public static void main(String[] args) throws Exception{

        //配置文件
        Configuration config = new Configuration();

        //job对象
        Job job = Job.getInstance(config);

        //指定jar包所在路径
        job.setJarByClass(FlowCount.class);

        //指定本业务job要使用的mapper/reducer业务类
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        //指定mapper输出的KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //指定最终数据的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

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
