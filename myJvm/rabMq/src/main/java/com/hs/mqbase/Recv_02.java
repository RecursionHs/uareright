package com.hs.mqbase;

import com.rabbitmq.client.*;

import java.io.IOException;


public class Recv_02 {
    private final static String QUEUE_NAME1 = "queue1";
    private final static String QUEUE_NAME2 = "queue2";
    private final static String QUEUE_NAME3 = "queue3";

    public static void main(String[] args) throws Exception {
        //获取连接及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //String exchange = "myExchange";
        channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
        //监听方式
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //获取并转成String
                String message = new String(body, "UTF-8");
                String exchange = envelope.getExchange();
                String routingKey = envelope.getRoutingKey();
                System.out.println("-->消费者2号，收到消息,msg :" + message + "|exchange:" +exchange + "|routingkey: " + routingKey);
            }
        };
        channel.basicConsume(QUEUE_NAME2,true,consumer);

    }
}