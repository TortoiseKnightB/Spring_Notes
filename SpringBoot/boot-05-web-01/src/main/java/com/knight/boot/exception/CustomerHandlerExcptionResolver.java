//package com.knight.boot.exception;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
//@Component
//public class CustomerHandlerExcptionResolver implements HandlerExceptionResolver {
//
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//
//        try {
//            response.sendError(511, "自定义错误异常");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView();
//    }
//}
