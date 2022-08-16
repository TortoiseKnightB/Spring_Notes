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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author TortoiseKnightB
 * @date 2022/08/15
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // 注入数据源
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);  // 自动创建表
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()    // 自定义自己编写的登录页面
                .loginPage("/login.html")  // 登录页面设置
                .loginProcessingUrl("/user/login")  // 登录访问路径
                .defaultSuccessUrl("/success.html").permitAll();   // 登录成功之后跳转路径

        http.authorizeRequests().antMatchers("/", "/test/hello", "/user/login").permitAll()    // 设置哪些路径可以直接访问，不需要认证
                .antMatchers("/test/index").hasAuthority("admins")    // 访问当前路径，需要用户具有admins权限
                .anyRequest().authenticated();  // 其他请求，需要认证

        http.csrf().disable();    // 关闭CSRF防护

        http.exceptionHandling().accessDeniedPage("/unauth.html");   // 如果用户没有权限访问，自动跳转该页面

        // 配置token自动登录
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)   // 设置有效时长60秒
                .userDetailsService(userDetailsService);
    }
}
