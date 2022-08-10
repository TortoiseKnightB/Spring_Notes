package com.knight.hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author TortoiseKnightB
 * @date 2022/08/10
 */
@ConfigurationProperties("knight.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
