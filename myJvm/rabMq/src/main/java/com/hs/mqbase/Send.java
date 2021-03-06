package com.hs.mqbase;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private final static String QUEUE_NAME = "bootQueue";

    public static void main(String[] args) throws Exception{
        //获取连接及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();

        //申明（创建）队列
        //channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //消息类容
        for (int i = 0; i < 1000; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            //Thread.sleep(i * 10);
        }
        //关闭通道连接
        channel.close();
        connection.close();
    }
}
