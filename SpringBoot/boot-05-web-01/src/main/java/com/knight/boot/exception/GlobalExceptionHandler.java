package com.knight.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个web的异常
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})  // 处理异常
    public String handleArithException(Exception e) {

        log.info("异常是：{}", e);
        return "home";      // 视图地址
    }


}
