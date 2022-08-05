package com.knight.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
@SpringBootTest
class Boot07TestApplicationTest {

    @DisplayName(value = "")
    @Test
    void test01() {
        System.out.println("hello world");
    }

}