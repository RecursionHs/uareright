package com.hs.mr.wcdemo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 这里的输入是map程序的输出
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //计数
        int countNum = 0;

        for(IntWritable value : values){
            countNum += value.get();
        }
        //输出
        context.write(key,new IntWritable(countNum));
    }
}
