package com.jk.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


public class TestRabbit {
    
    @RabbitHandler
    public void process(String msg){
        System.out.println("从队列中获取到="+msg);
    }

}
