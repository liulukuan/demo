package com.example.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: Producer
 * @description: 消息生产者
 * @author: LiuLukuan
 * @date: 2019/9/11 11:07
 */
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        rabbitTemplate.convertAndSend("msg", "this is send message!");
    }
}
