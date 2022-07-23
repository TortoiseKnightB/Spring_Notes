package com.knight.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@DisplayName("数据访问测试")
@Slf4j
@SpringBootTest
class Boot06DataApplicationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @DisplayName("JDBC连接")
    @Test
    void test01() {
        Long aLong = jdbcTemplate.queryForObject("SELECT count(*) FROM mybatis.t_cat;", Long.class);
        log.info("记录总数：{}", aLong);
    }

    @DisplayName("自定义数据源")
    @Test
    void test02() {
        log.info("数据源类型：{}", dataSource.getClass());
    }

}