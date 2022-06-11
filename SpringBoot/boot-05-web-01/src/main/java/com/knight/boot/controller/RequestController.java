package com.knight.boot.controller;

import com.knight.boot.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 不能带@ResponseBody，否则 forward:/ 失效
@Controller
public class RequestController {

    // http://localhost:8080/goto
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "test message");
        request.setAttribute("code", 314);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request) {
        Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("request_msg", msg1);
        map.put("annotation_msg", msg);
        return map;
    }


    // 复杂参数解析
    // http://localhost:8080/params
    @GetMapping("/params")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world6666");
        model.addAttribute("world", "hello666");
        request.setAttribute("message", "HelloWorld");
        Cookie cookie = new Cookie("c1", "v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success2";
    }

    @ResponseBody
    @GetMapping("/success2")
    public Map success2(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        map.put("hello", hello);
        map.put("world", world);
        map.put("message", message);
        return map;
    }


    // 异常处理流程
    // http://localhost:8080/getError
    @GetMapping("/getError")
    public String getError() {
        int i = 10 / 0;
        return "forward:/success2";
    }

    // 自定义异常
    // http://localhost:8080/myError
    @GetMapping("/myError")
    public String myError() {

        throw new MyException("我抛出了一个自定义异常");

    }

}
