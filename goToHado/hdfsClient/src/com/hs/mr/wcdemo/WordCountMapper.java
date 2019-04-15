package com.hs.mr.wcdemo;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    /**map阶段,一行一行的读文本处理*/
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**拿到一行文字*/
        String line = value.toString();
        /**分割一段文字为单词数组*/
        String[] strings = line.split(" ");

        /**将每个单词写到上下文中*/
        for(String word:strings){
            context.write(new Text(word),new IntWritable(1));
        }

    }
}
