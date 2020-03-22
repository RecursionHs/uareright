package com.hs.bootformq.controller;

import com.hs.bootformq.util.MqUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SendMessageController {

    @Autowired
    private MqUtil mqUtil;

    @GetMapping("/sendMessage")
    public String sendMessage(String exchange,String routingKey,String message){
        System.out.println("send message:" + message);
        mqUtil.sendMessage(exchange,routingKey,message);
        return "发送消息成功！";
    }

    @GetMapping("/sendMessageAck")
    public String sendMessageAck(String exchange,String routingKey,String message){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("send message:" + message + " correlationData:" + correlationData);
        mqUtil.sendMessage(exchange,routingKey,message,correlationData);
        return "发送消息成功！";
    }
}
