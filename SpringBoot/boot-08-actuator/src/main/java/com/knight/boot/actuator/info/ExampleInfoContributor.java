package com.knight.boot.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 方式二：编写InfoContributor
 * <p>
 * 在http://localhost:8080/actuator/info中查看
 *
 * @author TortoiseKnightB
 * @date 2022/08/07
 */
@Component
public class ExampleInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("msg", "hello")
                .withDetail("HELLO", "WORLD")
                .withDetails(Collections.singletonMap("world", "666"));
    }
}
