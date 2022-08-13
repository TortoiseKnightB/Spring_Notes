package com.knight.boot.config;

//import com.knight.boot.converter.KnightMessageConverter;
//import com.knight.boot.interceptor.LoginInterceptor;
import com.knight.boot.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableWebMvc // 启动开关
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        // 设置不移除分号（;）后面的内容，矩阵变量可以生效。基本没有，参考一下这个自定义的思路
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
//
//
//    // 自定义 MessageConverter
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new KnightMessageConverter());
//    }
//
//
//    // 自定义内容协商策略
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        Map<String, MediaType> mediaTypes = new HashMap<>();
//        mediaTypes.put("json", MediaType.APPLICATION_JSON);
//        mediaTypes.put("xml", MediaType.APPLICATION_ATOM_XML);
//        mediaTypes.put("tk", MediaType.parseMediaType("application/t-knight"));
//        // 指定支持解析哪些参数对应的哪些媒体类型
//        ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
//
//        HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
//        // 这个会取消掉默认的其他内容协商策略，可以尝试 configurer.mediaType()
//        configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy, headerContentNegotiationStrategy));
//    }


    // 添加拦截器

    /**
     * 1、编写一个拦截器实现HandlerInterceptor接口
     * <p>
     * 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
     * <p>
     * 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  // 所有请求都被拦截包括静态资源
                .excludePathPatterns("/", "/login", "/haha/**","/nologin"); // 放行的请求
    }


//    @Bean
//    public WebMvcRegistrations webMvcRegistrations(){
//        return new WebMvcRegistrations() {
//
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
//            }
//
//            @Override
//            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
//                return WebMvcRegistrations.super.getRequestMappingHandlerAdapter();
//            }
//
//            @Override
//            public ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
//                return WebMvcRegistrations.super.getExceptionHandlerExceptionResolver();
//            }
//        };
//    }
}
