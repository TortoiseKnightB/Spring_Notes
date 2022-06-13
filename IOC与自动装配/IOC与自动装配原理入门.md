# IOC与自动装配原理入门

### 反射

### IOC的基本使用

##### IOC

- 以前是自己new 对象，现在所有的对象交给容器创建；给容器中注册组件

- 容器：管理所有的组件。某个组件要使用Spring提供的更多的功能（IOC、AOP），必须要加入到容器中

- IOC（Inversion Of Control）：控制反转，是Spring里面的一个核心概念，就是对组件对象控制权的转移，从程序代码本身转移到了外部容器，由容器来创建对象并管理对象之间的依赖关系。简单来说就是，将**对象的创建与对象的调用**交给Spring容器统一管理

- DI（Dependency Injection）：依赖注入，DI是对IOC更准确的描述，组件之间的依赖关系由容器在运行期决定。通俗的解释，容器能知道组件（类）A在运行的时候，需要另外一个组件（类）B。通过反射的形式，**将容器中准备好的B对象注入（利用反射给属性赋值）到A对象中**

- 目的：降低程序之间的耦合度

- 原理：反射机制与工厂模式

------

- 一个简单的例子

- 不使用IOC，需要手动创建资源

```java
    public void test01() {
        Car car = new Car(300000.0, "Blue");
        Person person = new Person("张三", 20, car);
        System.out.println(person);
    }
```

- 使用IOC，从容器中调用资源

```java
    public void test02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Object person = ioc.getBean("myPerson");
        System.out.println(person);
    }
```

```xml
    <!-- ioc.xml 基于xml配置文件方式实现组件管理 -->
    <bean id="myPerson" class="com.knight.boot.bean.Person" scope="singleton">
        <property name="name" value="李四"></property>
        <property name="age" value="24"></property>
        <property name="car" ref="myCar"></property>
    </bean>

    <bean id="myCar" class="com.knight.boot.bean.Car">
        <property name="price" value="50000.0"></property>
        <property name="color" value="Red"></property>
    </bean>
```

- ioc.xml：配置文件，包含了组件（myPerson、myCar）的赋值和依赖关系
- ApplicationContext：代表ioc容器，管理配置文件中的所有组件

##### 一些细节

- scope：
  - prototype：多实例
    1、容器启动默认不会去创建实例bean
    2、获取的时候创建这个bean
    3、每次获取都会创建一个新的实例对象
  - singleton：单实例（默认）
    1、在容器启动完成之前已经创建好对象，保存在容器中
    2、任何时候获取都是之前创建好的那个对象
- ioc容器在创建组件对象的时候，(property)会利用setter方法为组件的属性进行赋值；

##### 通过工厂模式管理Bean

- 自定义工厂类——静态工厂

```java
public class CarStaticFactory {

    public static Car getCar(Double price, String color) {
        Car car = new Car(price, color);
        return car;
    }
}
```

```xml
    <!-- 静态工厂 -->
    <bean id="car01" class="com.knight.boot.factory.CarStaticFactory" factory-method="getCar">
        <constructor-arg name="price" value="50000"></constructor-arg>
        <constructor-arg name="color" value="Blue"></constructor-arg>
    </bean>
```

- 自定义工厂类——实例工厂

```java
public class CarInsanceFactory {

    public Car getCar(Double price, String color) {
        Car car = new Car(price, color);
        return car;
    }
}
```

```xml
    <!-- 实例工厂 -->
    <bean id="carInsanceFactory" class="com.knight.boot.factory.CarInsanceFactory"></bean>
    <bean id="car02" class="com.knight.boot.bean.Car" factory-bean="carInsanceFactory"
          factory-method="getCar">
        <constructor-arg name="price" value="80000"></constructor-arg>
        <constructor-arg name="color" value="Red"></constructor-arg>
    </bean>
```

- 实现FactoryBean接口

<p align="center">
        <img src="img/001.png" width="600" align=center  />
</p>

- FactoryBean接口的实现类，Spring都会认为是一个工厂
- 暴露出的对象为`getObject()`方法返回的对象

```java
public class CarFactoryBeanImp implements FactoryBean<Car> {

    @Override
    public Car getObject() {
        System.out.println("CarFactoryBeanImp...创建对象");
        Car car = new Car(66666.6, "Black");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
```

```xml
<bean id="carFactoryBeanImp" class="com.knight.boot.factory.CarFactoryBeanImp"></bean>
```

- ioc容器启动时不会创建实例
- 无论单实例还是多实例，都是在获取的时候创建对象

##### 注解的使用



### IOC原理

##### Bean的生命周期




### SpringBoot 自动装配原理入门
