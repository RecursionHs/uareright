package com.hs.bootformq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    /**
     * 连接配置
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("47.112.197.158", 8084);
        connectionFactory.setUsername("hs");
        connectionFactory.setPassword("123abc");
        connectionFactory.setVirtualHost("/ddSys");
        //是否开启消息确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 定义交换机的名字
     * @return
     */
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue queue(){
        //名字  是否持久化
        return  new Queue("bootQueue",true);
    }

    @Bean
    public Binding binding(){
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("direct.Key");
    }

    /**
     *  spring 提供了一个RabbitTemplate 来帮助我们完成发送消息的操作
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        //注意 这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的 如果是yml配置 的连接的话是不需要的
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //开启mandatory模式（开启失败回调）
        rabbitTemplate.setMandatory(true);
        //指定失败回调接口的实现类
        rabbitTemplate.setReturnCallback(new MyReturnCallBackMq());
        //发送方确认默认实现类
        rabbitTemplate.setConfirmCallback(new MyConfirmCallback());
        return rabbitTemplate;
    }


}
