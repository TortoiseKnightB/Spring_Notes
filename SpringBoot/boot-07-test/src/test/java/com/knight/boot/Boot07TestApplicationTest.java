package com.knight.boot;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link SpringBootTest @SpringBootTest}使得测试类可以使用{@link org.springframework.beans.factory.annotation.Autowired @Autowired}等Spring注解
 * <p>
 * <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-ValueSource">JUnit5文档</a>
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
@DisplayName("JUnit5测试")
@SpringBootTest
class Boot07TestApplicationTest {

    /**
     * 在所有单元测试前运行
     */
    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试就要开始了");
    }

    /**
     * 在所有单元测试后运行
     */
    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试就要结束了");
    }

    /**
     * 每一个单元测试前都运行
     */
    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试就要开始了");
    }

    /**
     * 每一个单元测试后都运行
     */
    @AfterEach
    void testAfterEach() {
        System.out.println("测试就要结束了");
    }


    @DisplayName(value = "基础测试")
    @Test
    void test01() {
        System.out.println("hello world");
    }


    @DisplayName(value = "Disabled")
    @Disabled
    @Test
    void test02() {
        System.out.println("该测试不会执行");
    }


    /**
     * 规定超时时间，超出时间测试出异常
     *
     * @throws InterruptedException
     */
    @DisplayName("Timeout")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void test03() throws InterruptedException {
        Thread.sleep(600);
        System.out.println("该测试超时");
    }


    @DisplayName("RepeatedTest")
    @RepeatedTest(5)
    @Test
    void test04() {
        System.out.println("重复测试");
    }


    @DisplayName("断言——assertEquals/assertNotEquals")
    @Test
    void testAssertions01() {
        int i = 3 + 3;
        assertEquals(5, i, "两个结果不一样");
    }


    @DisplayName("断言——assertSame/assertNotSame")
    @Test
    void testAssertions02() {
        Object o1 = new Object();
        Object o2 = new Object();
        assertSame(o1, o2, "两个对象不一样");
        assertNotSame(o1, o2, "前面失败，后面的代码不执行");
    }


    @DisplayName("断言——assertNull/assertNotNull")
    @Test
    void testAssertions03() {
        Object o1 = new Object();
        assertNotNull(o1, "对象为空");
        assertNull(o1, "对象不为空");
    }


    /**
     * 数组断言
     */
    @DisplayName("断言——assertArrayEquals")
    @Test
    public void array() {
        assertArrayEquals(new int[]{2, 1}, new int[]{1, 2}, "两个数组内容不相等");
    }


    /**
     * 组合断言，所有断言全部需要成功
     */
    @Test
    @DisplayName("断言——assertAll")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1, "计算结果不相等"),
                () -> assertTrue(1 > 2, "结果不为true")
        );

        System.out.println("断言失败，后面代码不执行");
    }


    /**
     * 异常断言，断定业务逻辑一定出现异常
     */
    @DisplayName("断言——assertThrows")
    @Test
    public void exceptionTest() {
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            int i = 10 / 2;
        }, "业务逻辑居然正常运行！");

    }


    @DisplayName("断言——assertTimeout")
    @Test
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }


    @DisplayName("断言——fail")
    @Test
    public void shouldFail() {
        fail("快速失败");
    }


    /**
     * 不满足的断言会使得测试方法失败，而不满足的前置条件只会使得测试方法的执行终止
     */
    @DisplayName("前置条件测试")
    @Test
    void testAssumptions() {
        Assumptions.assumeTrue(true, "结果不是true");
        System.out.println("。。。继续执行代码");
        Assumptions.assumingThat(true, () -> System.out.println("。。。满足条件，输出该语句"));
    }


}