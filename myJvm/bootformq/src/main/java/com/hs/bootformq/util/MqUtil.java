package com.hs.bootformq.util;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 参数介绍： 交换机名字，路由键， 消息内容
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendMessage(String exchange,String routingKey,String message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    /**
     * 参数介绍： 交换机名字，路由键， 消息内容,CorrelationData参数 这个对象可以设置一个id
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendMessage(String exchange, String routingKey, String message, CorrelationData correlationData){
        rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData);
    }


}
