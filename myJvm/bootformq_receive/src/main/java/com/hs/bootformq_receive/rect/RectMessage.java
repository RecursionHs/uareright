package com.hs.bootformq_receive.rect;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class RectMessage {

    //普通的一个接受消息
    //@RabbitListener(queues="bootQueue")
    public void get(String message){
        System.out.println("消费者1接受消息：" + message);
    }

    @RabbitListener(queues = "bootQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void getMessage1(Message message, Channel channel) throws Exception {
        //模拟下单
        if(playOrder()){
            System.out.println("消费者1接受消息： " + new String(message.getBody(),"UTF-8"));
            //成功就确认消息
            //message.getMessageProperties().getDeliveryTag()等于是获取此条消息的id
            //第二个boolean参数：是否批量处理
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else{
            //失败了就回退此条消息
            System.out.println("到死信队列...");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }

    @RabbitListener(queues = "ded.que",containerFactory = "simpleRabbitListenerContainerFactory")
    public void getMessage2(Message message, Channel channel) throws Exception {
        //确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("消 费 者 2 接 受 消 息： " + new String(message.getBody(),"UTF-8"));

    }

    public static Boolean playOrder(){
        Random random = new Random();
        int i = random.nextInt(100);
        if(i%2 == 0){
            return true;
        }
        return false;
    }
}
