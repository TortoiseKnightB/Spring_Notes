package com.knight.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "自定义异常")
public class MyException extends RuntimeException {

    public MyException(String message) {
        super(message);
        log.info("我接收到了自定义异常：{}", message);
    }
}
