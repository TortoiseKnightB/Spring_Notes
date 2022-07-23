package com.knight.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.knight.boot.controller.HomeController;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
public class MyDataSourceConfig {

    /**
     * 自定义DataSource，使自动配置的数据源{@link org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Hikari Hikari}失效
     *
     * @return
     * @throws SQLException
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        // 加入监控功能 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatFilter
        // 打开防火墙 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter
        // 也可以在application.yml文件中配置
        druidDataSource.setFilters("stat,wall");

        return druidDataSource;
    }


    /**
     * 配置 druid 的监控页功能，<a href="http://localhost:8080/druid/index.html">监控地址: http://localhost:8080/druid/index.html</a>
     *
     * @return
     * @see <a href="https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatFilter">打开Druid的监控统计功能</a>
     * @see <a href="https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE">使用Druid的内置监控页面</a>
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        // 配置登录名和密码
//        registrationBean.addInitParameter("loginUsername", "admin");
//        registrationBean.addInitParameter("loginPassword", "password");
        return registrationBean;
    }

    /**
     * WebStatFilter 用于采集web-jdbc关联监控的数据
     *
     * @return
     * @see <a href="https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_%E9%85%8D%E7%BD%AEWebStatFilter">内置监控中的Web和Spring关联监控</a>
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        WebStatFilter druidWebStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(druidWebStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 配置_Druid和Spring关联监控配置
     *
     * @return
     * @see <a href="https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_Druid%E5%92%8CSpring%E5%85%B3%E8%81%94%E7%9B%91%E6%8E%A7%E9%85%8D%E7%BD%AE">配置_Druid和Spring关联监控配置</a>
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
        return druidStatInterceptor;
    }

    @Bean
    public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator(DruidStatInterceptor druidStatInterceptor) {
        BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
        // 被拦截监控的Bean类型
        beanTypeAutoProxyCreator.setTargetBeanType(HomeController.class);
        beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanTypeAutoProxyCreator;
    }

}
