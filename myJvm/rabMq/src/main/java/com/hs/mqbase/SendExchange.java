package com.hs.mqbase;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SendExchange {
    private final static String QUEUE_NAME1 = "queue1";
    private final static String QUEUE_NAME2 = "queue2";
    private final static String QUEUE_NAME3 = "queue3";

    public static void main(String[] args) throws Exception{
        //获取连接及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        String exchange = "myExchange";
        channel.exchangeDelete(exchange);
        channel.exchangeDeclare(exchange,"topic");

        channel.queueBind(QUEUE_NAME1,exchange,"order.*.*");
        channel.queueBind(QUEUE_NAME2,exchange,"*.debug.A");
        channel.queueBind(QUEUE_NAME3,exchange,"order.#");

        String[] s1 = new String[]{"order","pay","login"};
        String[] s2 = new String[]{"debug","info","WARN"};
        String[] s3 = new String[]{"A","B","C"};
        String  ROUTING_KEY = "";
         for(int i = 0;i < s1.length;i++){
            for (int j = 0;j < s2.length;j++){
                for(int k = 0;k < s3.length;k++){
                    ROUTING_KEY = s1[i] + "." + s2[j] + "." + s3[k];
                    System.out.println(ROUTING_KEY);
                    channel.basicPublish(exchange,ROUTING_KEY,null,"haha".getBytes());
                }
            }
        }

        //关闭通道连接
        channel.close();
        connection.close();
    }
}
