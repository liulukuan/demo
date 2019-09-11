package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: Consumer
 * @description: 消息消费者
 * @author: LiuLukuan
 * @date: 2019/9/11 11:10
 */
@Component
@RabbitListener(queues = "simpleQueue")
public class Consumer {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("The msg is: " + msg);
    }
}
