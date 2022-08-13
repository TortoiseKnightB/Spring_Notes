package com.knight.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 嵌套测试
 *
 * @author TortoiseKnightB
 * @date 2022/08/07
 */
@DisplayName("嵌套测试")
public class TestingAStackDemo {

    Stack<Object> stack;

    @DisplayName("new Stack()")
    @Test
    void isInstantiatedWithNew() {
        new Stack<>();
        // 嵌套测试的情况下，外层的Test不能驱动内层的BeforeEach/All之类的方法
        assertNull(stack);
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
            System.out.println("。。。生成新的栈");
        }

        @DisplayName("is empty")
        @Test
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        /**
         * 栈为空，栈pop元素时抛出异常
         */
        @DisplayName("throws EmptyStackException when popped")
        @Test
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        /**
         * 栈为空，栈peek元素时抛出异常
         */
        @DisplayName("throws EmptyStackException when peeked")
        @Test
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
                System.out.println("。。。栈中放入数据");
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                // 嵌套测试的情况下，内层的Test可以驱动外层的BeforeEach/All之类的方法
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
