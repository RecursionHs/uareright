package com.hs.bootformq_receive.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

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
        //connectionFactory.setPublisherConfirms(true);
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
    public TopicExchange topicExchange(){
        return new TopicExchange("ded.exchange");
    }

    @Bean
    public Queue queueDed(){
        //名字  是否持久化
        return  new Queue("ded.que",true);
    }

    /**
     * 给队列配置死信交换机
     * @return
     */
    @Bean
    public Queue queue(){
        HashMap<String, Object> map = new HashMap<>();
        //设置消息的过期时间(毫秒)，消息过期后，消息会失效
        map.put("x-message-ttl",10000);
        //设置附带的死信交换机
        map.put("x-dead-letter-exchange","ded.exchange");
        //指定重定向的路由建 消息作废之后可以决定需不需要更改他的路由建 如果需要 就在这里指定
        map.put("x-dead-letter-routing-key","ded.key");
        return new Queue("bootQueue",true,false,false,map);

    }

    @Bean
    public Binding binding(){
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("direct.Key");
    }
    @Bean
    public Binding bindingDed(){
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queueDed()).to(topicExchange()).with("ded.#");
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
        return rabbitTemplate;
    }

    /**
     * 声明一个监听容器，对容器进行配置，手动确认的方式
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消息预取
        simpleRabbitListenerContainerFactory.setPrefetchCount(1);
        return simpleRabbitListenerContainerFactory;
    }

}
