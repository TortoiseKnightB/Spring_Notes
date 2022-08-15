package com.knight.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 方式三：自定义实现类设置登录系统的账号、密码
 *
 * @author TortoiseKnightB
 * @date 2022/08/15
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()    // 自定义自己编写的登录页面
                .loginPage("/login.html")  // 登录页面设置
                .loginProcessingUrl("/user/login")  // 登录访问路径
                .defaultSuccessUrl("/test/index").permitAll();   // 登录成功之后跳转路径

        http.authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll()    // 设置哪些路径可以直接访问，不需要认证
                .antMatchers("/test/index").hasAuthority("admins")    // 访问当前路径，需要用户具有admins权限
                .antMatchers("/test/home").hasAnyAuthority("admins", "manager")    // 访问当前路径，需要admins,manager中任一权限
                .antMatchers("/test/sale").hasRole("sale")      // 访问当前路径，需要用户具有ROLE_sale权限
                .antMatchers("/test/anySale").hasAnyRole("sale", "admin")    // 访问当前路径，需要用户具有ROLE_sale、ROLE_admin权限
                .anyRequest().authenticated();  // 其他请求，需要认证

        http.csrf().disable();    // 关闭CSRF防护

        http.exceptionHandling().accessDeniedPage("/unauth.html");   // 如果用户没有权限访问，自动跳转该页面


    }
}
