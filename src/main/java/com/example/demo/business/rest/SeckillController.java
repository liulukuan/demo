package com.example.demo.business.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className: SeckillController
 * @description: 秒杀场景，分布式锁实现
 * @author: LiuLukuan
 * @date: 2019/9/19 12:13
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/do")
    public String seckill() {

        String lockKey = "lockKey";
        String lockId = UUID.randomUUID().toString();
        String stockKey = "stock";

        try {
            boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockId, 2, TimeUnit.SECONDS);
            if (!result) {
                return "";
            }

            int oldStock = (Integer) redisTemplate.opsForValue().get(stockKey);
            if (oldStock > 0) {
                int nowStock = oldStock - 1;
                redisTemplate.opsForValue().set(stockKey, nowStock);
                System.out.println("扣减库存成功,当前库存为: " + nowStock);
            } else {
                System.out.println("库存不足!");
            }
        } finally {
            if (lockId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }
        return "end";
    }
}
