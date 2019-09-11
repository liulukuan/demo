package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @className: RabbitConfig
 * @description: rabbitMQ配置类
 * @author: LiuLukuan
 * @date: 2019/9/11 11:03
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue simpleQueue() {
        return new Queue("simpleQueue");
    }
}
