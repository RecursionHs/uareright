package com.hs.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class HdfsStreamAccess {
    FileSystem fs = null;
    Configuration conf = null;
    @Before
    public void init() throws Exception{
        conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://new1:9000");
        //拿到一个文件系统操作的客户端实例对象
        fs = FileSystem.get(new URI("hdfs://new1:9000"),conf,"hadoop");
    }

    /**
     * 通过流的方式上传文件到HDFS
     * @throws IOException
     */
    @Test
    public void testUpload()throws IOException {
        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/haha.tt"), true);
        FileInputStream fileInputStream = new FileInputStream("F:\\studyVideo\\java\\课件\\day07\\day06的问题总结.txt");
        IOUtils.copyBytes(fileInputStream,fsDataOutputStream,conf);
    }

    /**
     * 流的方式获取HDFS上面的文件
     * @throws IOException
     */
    @Test
    public void testDownLoad()throws IOException{
        FSDataInputStream fdInputStrem = fs.open(new Path("/haha.tt"));
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\studyVideo\\java\\课件\\day07\\haha.tt");
        IOUtils.copyBytes(fdInputStrem,fileOutputStream,conf);
    }

    @Test
    public void testRandomAccess()throws IOException{
        FSDataInputStream fsDataInputStream = fs.open(new Path("/haha.tt"));
        fsDataInputStream.seek(12);
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\studyVideo\\java\\课件\\day07\\haha2.tt");
        IOUtils.copyBytes(fsDataInputStream,fileOutputStream,conf);
    }

    @Test
    public void testCat()throws IOException{
        FSDataInputStream fsDataInputStream = fs.open(new Path("/haha.tt"));
        IOUtils.copyBytes(fsDataInputStream,System.out,conf);
    }
}
