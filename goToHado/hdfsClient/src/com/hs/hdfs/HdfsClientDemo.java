package com.hs.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/**
 * 客户端去操作hdfs时，是有一个用户身份的
 * 默认情况下，回去JVM中获取HADOOP_USER_NAME属性的值.在启动参数设置
 *
 * 也可以在构造客户端fs对象是，传入用户名
 */
public class HdfsClientDemo{
    FileSystem fs = null;
    Configuration conf = null;

    /**
     * 配置连接
     * @throws Exception
     */
    @Before
    public void init() throws Exception{
        conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://new1:9000");
        //拿到一个文件系统操作的客户端实例对象
        fs = FileSystem.get(new URI("hdfs://new1:9000"),conf,"hadoop");
    }

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void upload() throws Exception{
        fs.copyFromLocalFile(new Path("c:/APlayerUI.dll"),new Path("/APlayerUI.dll.copy"));
        fs.close();
    }

    /**
     * 下载文件
     * @throws Exception
     */
    @Test
    public void getFile() throws Exception{
        fs.copyToLocalFile(new Path("/DapCtrl.dll.copy"),new Path("D:/softInstall/jdk/"));
        fs.close();
    }

    /**
     * 打印默认配置参数
     */
    @Test
    public void printConf(){
        Iterator<Map.Entry<String, String>> iterator = conf.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> co = iterator.next();
            System.out.println(co.getKey() + " : " + co.getValue());
        }
    }

    /**
     * 创建文件夹
     * @throws IOException
     */
    @Test
    public void testMkdir() throws IOException {
        boolean mkdirs1 = fs.mkdirs(new Path("/MK/aa/bb/cc"));
        boolean mkdirs2 = fs.mkdirs(new Path("/MK/xxfiledir"), new FsPermission(FsAction.ALL, FsAction.READ_EXECUTE, FsAction.NONE, false));
        System.out.println(mkdirs1);
        System.out.println(mkdirs2);
    }

    /**
     * 删除
     */
    @Test
    public void testDelet()throws IOException {
        boolean flag = fs.delete(new Path("/MK/aa/bb/cc"), true);
        System.out.println(flag);
    }

    /**
     * 递归显示指定文件夹里面的文件
     * @throws IOException
     */
    @Test
    public void testList()throws IOException{
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while(listFiles.hasNext()){
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("BlockSize:" + fileStatus.getBlockSize() + " Owner:" + fileStatus.getOwner() + " Replication:" + fileStatus.getReplication() + " Permission:" + fileStatus.getPermission() + " Name:" + fileStatus.getPath().getName());
            System.out.println("------------------------");
        }
    }

    @Test
    public void testList2()throws IOException{
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for(FileStatus file : fileStatuses){
            System.out.println("Name:" + file.getPath().getName());
            System.out.println(file.isFile()?"file":"directory");
        }
    }
}
