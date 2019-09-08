package com.hs.mr.rjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RJoin {


    static  class RJoinMapper extends Mapper<LongWritable,Text,Text,InfoBean>{
        InfoBean infoBean = new InfoBean();
        Text vkey = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            //pid
            String pid = "";
            /**获取分片文件*/
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String name = fileSplit.getPath().getName();
            /**根据文件名匹配不同的bean*/
            if(name.startsWith("order")){
                String[] strings = line.split(",");
                pid = strings[2];
                infoBean.set(Integer.parseInt(strings[0]),strings[1],pid,Integer.parseInt(strings[3]),"",0,0,"0");
             }else {
                String[] strings = line.split(",");
                pid = strings[0];
                infoBean.set(0,"",pid,0,strings[1],Integer.parseInt(strings[2]),Float.parseFloat(strings[3]),"1");
            }
            vkey.set(pid);
            context.write(vkey,infoBean);

        }
    }

    static class RJoinReducer extends Reducer<Text,InfoBean,InfoBean, NullWritable>{

  d  }
}
