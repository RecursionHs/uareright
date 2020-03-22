package com.hs.mqbase;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection()throws Exception{
        //定义连接工程
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("47.112.197.158");
        //端口
        factory.setPort(8084);
        //账号信息
        factory.setVirtualHost("/ddSys");
        factory.setUsername("hs");
        factory.setPassword("123abc");
        //通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
