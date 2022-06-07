package com.knight.boot.controller;

import com.knight.boot.bean.Person;
import com.knight.boot.bean.Pet;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {


    // http://localhost:8080/car/3/owner/ben?age=1&inters=ball&inters=game
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam MultiValueMap<String, String> params
//            ,
//                                      @CookieValue("SL_G_WPT_TO") String _cookie,
//                                      @CookieValue("SL_G_WPT_TO") Cookie cookie
    ) {


        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("headers", header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
//        map.put("SL_G_WPT_TO", _cookie);
//        System.out.println(cookie.getName() + "===>" + cookie.getValue());
        return map;
    }


    // http://localhost:8080/save
    @PostMapping("/save")
    public Map postMethod(@RequestBody Pet content) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(content);
        map.put("content", content);
        return map;
    }


    //1、语法： 请求路径：http://localhost:8080/cars/sell;low=34;brand=byd,audi,yd
    //2、SpringBoot默认是禁用了矩阵变量的功能
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。(参考com.knight.WebConfig#configurePathMatch())
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3、矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;

    }


    // 自定义对象参数
    // http://localhost:8080/saveuser
//    {
//        "username": "张三",
//            "age": 24,
//            "birth": "",
//            "pet": {
//        "name": "阿猫",
//                "weight": 18
//    }
//    }
    @PostMapping("/saveuser")
    public Person saveUser(@RequestBody Person person) {
        return person;
    }

}
