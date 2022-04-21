package com.knight.inter;

/**
 * 接口不加载到容器中
 * <p>
 * 实际上可以加@Component注解，不过加了也不会在容器中创建对象
 */
public interface Calculator {

    public int add(int i, int j);

    public int div(int i, int j);
}
