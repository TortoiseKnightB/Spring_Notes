# IOC与自动装配原理入门

### 反射

### IOC

- 以前是自己new 对象，现在所有的对象交给容器创建；给容器中注册组件

- IOC（Inversion Of Control）：控制反转，是Spring里面的一个核心概念，将**对象的创建与对象的调用**交给Spring统一管理

- 目的：降低程序之间的耦合度

- 原理：反射机制与工厂模式

------

- 在使用IOC以前，需要手动创建资源

```java
    public void test01() {
        Car car = new Car(300000.0, "Blue");
        Person person = new Person("张三", 20, car);
        System.out.println(person);
    }
```

- 使用IOC后，从容器中调用资源

```java
    public void test02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Object person = ioc.getBean("myPerson");
        System.out.println(person);
    }
```

- 容器：管理所有的组件（有功能的类）；假设，BookServlet受容器管理，BookService也受容器管理；容器可以自动的探查出那些组件（类）需要用到另一写组件（类）；容器帮我们创建BookService对象，并把BookService对象赋值过去；

- 容器：主动的new资源变为被动的接受资源；

- DI：（Dependency Injection）依赖注入；

- 容器能知道哪个组件（类）运行的时候，需要另外一个类（组件）；容器通过反射的形式，将容器中准备好的BookService对象注入（利用反射给属性赋值）到BookServlet中；

```
public class IOCTest {
    /**
     * 从容器中拿到这个组件
     */
    @Test
    public void test() {
        //ApplicationContext：代表ioc容器
        //ClassPathXmlApplicationContext:当前应用的xml配置文件在 ClassPath下
        //根据spring的配置文件得到ioc容器对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");

        //容器帮我们创建好对象了；
        Person bean = (Person) ioc.getBean("person01");

        System.out.println(bean);

    }

}
```

### SpringBoot 自动装配原理入门
