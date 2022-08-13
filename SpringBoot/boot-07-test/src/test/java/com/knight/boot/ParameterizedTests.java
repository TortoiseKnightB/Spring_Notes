package com.knight.boot;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

/**
 * 参数化测试，使用不同的参数进行测试
 *
 * @author TortoiseKnightB
 * @date 2022/08/07
 */
@DisplayName("参数化测试")
public class ParameterizedTests {

    @DisplayName("参数化测试1")
    @ValueSource(strings = {"one", "two", "three"})
    @ParameterizedTest
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }


    @DisplayName("方法来源参数")
//    @MethodSource("method")    //指定方法名
    @MethodSource("method2")    //指定方法名
    @ParameterizedTest
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }

    static String[] method2() {
        return new String[]{"AAA", "BBB", "CCC"};
    }
}
