package com.hs.mqbase;

import com.rabbitmq.client.*;

import java.io.IOException;


public class Recv {
    private final static String QUEUE_NAME = "hs_qu";

    public static void main(String[] args) throws Exception {
        //获取连接及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //定义通道
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //同一时刻服务器只会发一条消息给消费者，有ack回复后，才发下一条,这个加上手动ack可以使消息服务器服务器根据消费者服务器自身处理能力来处理消息。
        channel.basicQos(1);

       /* // 获取消息、方式一：循环方式
        //定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列,false表示手动返回状态,true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message + "'");
            //休眠
            Thread.sleep(2000);
            // 返回确认状态，注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }*/
        //监听方式
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //获取并转成String
                String message = new String(body, "UTF-8");
                String exchange = envelope.getExchange();
                String routingKey = envelope.getRoutingKey();
                System.out.println("-->消费者1号，收到消息,msg :" + message + "exchange:" +exchange + "routingkey: " + routingKey);
                //休眠
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);//手动确认
                }

            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }
}