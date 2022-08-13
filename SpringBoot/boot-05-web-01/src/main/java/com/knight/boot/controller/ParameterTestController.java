package com.knight.boot.controller;

import com.knight.boot.bean.Person;
import com.knight.boot.bean.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    /**
     http://localhost:8080/saveuser
     {
     "username": "张三",
     "age": 24,
     "birth": "",
     "pet": {
     "name": "阿猫",
     "weight": 18
     }
     }
     **/
    /**
     * 1、浏览器发请求直接返回 xml    [application/xml]        jacksonXmlConverter
     * <p>
     * 2、如果是ajax请求 返回 json   [application/json]      jacksonJsonConverter
     * <p>
     * 3、如果硅谷app发请求，返回自定义协议数据  [appliaction/x-guigu]   xxxxConverter
     * <p>
     * 属性值1;属性值2;
     * <p>
     * 步骤：
     * 1、添加自定义的MessageConverter进系统底层
     * <p>
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * <p>
     * 3、客户端内容协商 [guigu--->guigu]
     */
    @PostMapping("/saveuser")
    public Person saveUser(@RequestBody Person person) {
        return person;
    }


    // 简单代替登录页面
    // http://localhost:8080/login?username=admin&password=123456
    @GetMapping("/login")
    public String login(String username, String password, HttpSession session) {

        System.out.println("当前执行方法为：/login");

        if (StringUtils.hasLength(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return session.getAttribute("loginUser") + " login success";
        }
        return "login fail";
    }

    // 代替登录后的用户信息页面
    @GetMapping("/userInfo")
    public String userInfo(HttpSession session) {

        System.out.println("当前执行方法为：/userInfo");

        return session.getAttribute("loginUser") + "";
    }


    // 文件上传
    // 由于前面配置了拦截器，先登录 http://localhost:8080/login?username=admin&password=123456
    // 再上传文件 http://localhost:8080/uploadPage.html

    /**
     * MultipartFile 自动封装上传过来的文件
     */
    @PostMapping("/nologin/upload")
    public String upload(@RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        String info = "上传的信息：headerImg=" + headerImg.getSize() + "，photos=" + photos.length;

        if (!headerImg.isEmpty()) {
            // 保存到文件服务器，OSS服务器
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("F:\\cache\\" + originalFilename));
        }

        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("F:\\cache\\" + originalFilename));
                }
            }
        }

        return info;
    }


}
