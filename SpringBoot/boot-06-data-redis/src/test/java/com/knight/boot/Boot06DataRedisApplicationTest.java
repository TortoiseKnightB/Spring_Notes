package com.knight.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author TortoiseKnightB
 * @date 2022/08/04
 */
@SpringBootTest
class Boot06DataRedisApplicationTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @DisplayName("Redis连接")
    @Test
    void test01() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("k3", "v3");
        System.out.println(operations.get("hello"));
        System.out.println(operations.get("HELLO"));
        System.out.println(operations.get("k1"));
        System.out.println(operations.get("k2"));
        System.out.println(operations.get("k3"));
    }
}