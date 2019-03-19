package com.hs.thread;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * 客户端去操作hdfs时，是有一个用户身份的
 * 默认情况下，回去JVM中获取HADOOP_USER_NAME属性的值.在启动参数设置
 *
 * 也可以在构造客户端fs对象是，传入用户名
 */
public class HdfsClientDemo{
    FileSystem fs = null;

    @Before
    public void init() throws Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://new1:9000");
        //拿到一个文件系统操作的客户端实例对象
        fs = FileSystem.get(new URI("hdfs://new1:9000"),conf,"hadoop");
    }

    @Test
    public void upload() throws Exception{
        fs.copyFromLocalFile(new Path("c:/APlayerUI.dll"),new Path("/APlayerUI.dll.copy"));
        fs.close();
    }

    @Test
    public void getFile() throws Exception{
        fs.copyToLocalFile(new Path("/DapCtrl.dll.copy"),new Path("D:/softInstall/jdk/"));
        fs.close();
    }

}
