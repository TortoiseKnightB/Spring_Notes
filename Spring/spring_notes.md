# IOC

- ioc 是一个容器，帮我们管理所以组件
- 依赖注入 @Autowired 自动赋值
- 某个组件要使用Spring提供的更多功能（IOC、AOP），必须加入到容器中

### bean的作用域

- prototype：多实例
  1、容器启动默认不会去创建实例bean
  2、获取的时候创建这个bean
  3、每次获取都会创建一个新的实例对象
- singleton：单实例，默认的
  1、在容器启动完成之前已经创建好对象，保存在容器中
  2、任何时候获取都是之前创建好的那个对象

```xml
    <bean id="book" class="com.knight.bean.Book" scope="prototype">
        <property name="bookName" value="红楼梦"></property>
    </bean>
```

### FactoryBean接口

- 实现了FactoryBean接口的实现类都是Spring可以认识的工厂类
  1、ioc容器启动时不会创建实例
  2、无论单实例还是多实例，都是在获取的时候创建对象
- **数据库连接池**作为**单实例**是最好的：
  - 一个项目就一个连接池，连接池里面管理很多连接
  - 可以让Spring管理连接池

```xml
<bean id="myFactoryBeanImp" 
      class="com.knight.factory.MyFactoryBeanImp"></bean>
```

```java
public class MyFactoryBeanImp implements FactoryBean<Book> {

    /**
     * 返回创建的对象
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("MyFactoryBeanImp...创建对象");
        Book book = new Book();
        book.setBookName(UUID.randomUUID().toString());
        return book;
    }

    /**
     * 返回创建的对象的类型
     * Spring自动调用，来确认创建的对象是什么类型
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * 是否单例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
```

### 引用外部属性文件

```xml
<!-- 引用外部属性文件，依赖context名称空间 -->
<!-- 加载外部配置文件。固定写法classpath:表示引用类路径下的文件 -->
<context:property-placeholder location="classpath:dbconfig.properties"/>

<bean id="car" class="com.knight.bean.Car">
    <property name="carName" value="${jdbc.username}"></property>
    <property name="color" value="${password}"></property>
</bean>
```

### 注解

- 通过给bean上添加某些注解，可以快速将bean加入到ioc容器中

- Spring有4个注解：
  1、@Controller：控制器层，给控制器（Servlet包下的）组件添加这个注解
  2、@Service：业务逻辑层注解
  3、@Repository：给数据层（持久化层，Dao层）的组件添加这个注解
  4、@Component：给不属于以上几层的组件添加这个注解

- 使用注解步骤：
  1、给要添加的组件上标示4个注解中的任何一个
  2、告诉Spring，自动扫描加了注解的组件；依赖context名称空间

```xml
<!-- context:component-scan 自动扫描组件    
             base-package：自动扫描的基础包路径-->
<context:component-scan base-package="com.knight">
</context:component-scan>
```

- 组件 id: 默认为类名首字母小写
  - 自定义：@Service("BookService")
- 组件作用域：默认为单例
  - 自定义：@Scope(value = "prototype")

```java
@Repository
@Scope(value = "prototype")
public class BookDao {
    public void save(){
        System.out.println("图书已经保存...");
    }
}



@Service("BookService")
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void save() {
        System.out.println("BookService...启动");
        bookDao.save();
    }
}
```

- **@Autowired** 原理：
  - 先按照类型去容器中找到对应组件；`bookService = ioc.getBean(BookService.class);`
    - 若没找到，则抛异常；
    - 若找到多个，则按照变量名作为 id 继续匹配，若仍没匹配上则报错
- **@Qualifier** 可以指定 id
- @Autowired(required = false)，找不到会返回null
- @Autowired标注在方法上
  - 这个方法也会在bean创建的时候自动运行
  - 这个方法上的每一个参数都会自动注入，匹配规则与上相同，并且可以用@Qualifier修饰
- **@Autowired**、**@Resource**区别
  - @Autowired：最强大，是Spring自己的注解
  - @Resource：j2ee，java的标准
  - @Resource 扩展性强，是java的标准，切换成另外的容器框架还能用

### spring单元测试

- 用 `@ContextConfiguration(locations = "")` 来指定 Spring 配置文件的位置
- 用 `@RunWith` 指定用哪种驱动进行单元测试，默认 JUnit
  - 好处：不用 `ioc.getBean()` 获取组件，直接 `@Autowired` 自动装配

```java
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    BookServlet bookServlet;

    /**
     * 此处用JUnit4，而非JUnit5
     */
    @Test
    public void test1() {
        bookServlet.doGet();
    }
}
```

### 泛型依赖注入

- 通过带泛型的父类类型确定注入的子类

```java
BirdService extends BaseService<Bird>
PlaneService extends BaseService<Plane>

BirdDao extends BaseDao<Bird>
PlaneDao extends BaseDao<Plane>

// 带泛型的父类类型
birdService.getClass().getGenericSuperclass()
```

------

# AOP

- 加强版的面向切面编程，即使目标对象没有实现任何接口也能创建动态代理

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
```

- 两种实现方式：
  - 注解：快速方便，绝大部分都用这个，不方便定位
  - xml配置：功能完善
  - 重要的切面用配置，不重要的切面用注解

### 基本使用

- **@Aspect** 五个通知注解：
  
  - @Before 前置通知：在目标方法执行之前执行
  - @After 后置通知：在目标方法结束之后执行
  - @AfterReturning 返回通知：在目标方法正常返回之后执行
  - @AfterThrowing 异常通知：在目标方法抛出异常之后执行
  - @Around 环绕通知：最强的通知，一个顶上面四个

- 步骤：
  1、将目标类和切面类（封装了通知方法）加入到ioc容器中
  2、告诉Spring到底哪个是切面类@Aspect
  3、告诉Spring，切面类里面的每一个方法，都是何时何地运行
  4、开启基于注解的AOP模式

```xml
<context:component-scan base-package="com.knight"></context:component-scan>

<!-- 开启基于注解的aop功能，需要aop名称空间 -->
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```

- **@Order**：使用 `@Order` 改变切面顺序，数值越小，优先级越高

### 代理对象

- AOP 的底层就是动态代理，ioc容器中保存的是代理对象 $Proxy，不是本类类型
  - 从ioc容器中拿到目标对象，如果用 类型方式获取，记得用 它的接口类型，不要用它本类
  - 实现了aop后获取的不再是本类对象，而是代理对象$Proxy,$Proxy也继承了Calculator，所以获取本类的话ioc.getBean(MyMathCalculator.class）会报错

```java
// MyMathCalculator implements Calculator
// Calculator bean = ioc.getBean(MyMathCalculator.class); 报错，容器中创建的对象是 $Proxy implements Calculator
Calculator bean = ioc.getBean(Calculator.class);
```

- 如果类没有实现接口的话，那么容器创建的就是本类代理对象（由CGLIB创建）

### 切入点表达式

- 固定格式：`execution(访问权限符 返回值类型 方法全类名(参数表))`
- 通配符 *****：
  - 匹配一个或多个字符
  - 匹配任意一个参数
  - 只能匹配一层路径
  - 权限位置不能写 *，权限位置不写就行，public 可选
- 通配符 **..**：
  - 匹配任意多个参数，任意类型参数
  - 匹配任意多层路径
- 最模糊写法：`execution(* *(..))`，千万别写这种
- 最精确写法：`execution(public int com.knight.impl.MyMathCalculator.add(int, int))`
- 还可以加上 `&&`、`||`、`!`
- 抽取可重用的切入点表达式

```java
    // 抽取可重用的切入点表达式
    @Pointcut("execution(public int com.knight.impl.MyMathCalculator.*(int, int))")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public static void logStart(JoinPoint joinPoint) {
        // todo
    }
```

### 切入点信息

- 这里的参数不能随便传
- **JoinPoint** 封装了目标方法当前的详细信息

```java
    @Before("execution(public int com.knight.impl.MyMathCalculator.*(int, int))")
    public static void logStart(JoinPoint joinPoint) {
        // 获取目标方法运行使用的参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("【" + signature.getName() + "】方法开始执行，用的参数列表【" + Arrays.asList(args) + "】");
    }
```

- `returning = "result"`，告诉spring用result来接收目标方法返回值

```java
    @AfterReturning(value = "execution(public int com.knight.impl.MyMathCalculator.*(int, int))", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("【" + joinPoint.getSignature().getName() + "】方法正常执行完成，计算结果是：" + result);
    }
```

- `throwing = "exception"`，告诉spring用 exception 来接收目标方法返回值

```java
    @AfterThrowing(value = "execution(public int com.knight.impl.MyMathCalculator.*(int, *))", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("【" + joinPoint.getSignature().getName() + "】方法执行出现异常了，异常信息是：" + exception);
    }
```

### 声明式事务

- 事务管理代码可以通过 AOP 方法模块化，借助 Spring AOP 框架实现声明式事务管理，在目标方法运行前后进行事务控制（事务切面）
- 使用 PlatformTransactionManager 下的 DataSourceTransactionManager
- 具体配置步骤：

```xml
    <!-- 加载外部配置文件。固定写法classpath:表示引用类路径下的文件 -->
    <context:property-placeholder location="classpath:dbconfig.properties"/>

    <!--  配置数据源-->
    <bean id="pooledDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
        <property name="driverClass" value="${jdbc.driverClass}"></property>
    </bean>

    <!-- 配置JdbcTemplate -->
    <bean class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="pooledDataSource"></property>
    </bean>

    <!-- 事务控制 -->
    <!--1:配置事务管理器（切面）让其进行事务控制-->
    <bean id="tm" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 控制住数据源 -->
        <property name="dataSource" ref="pooledDataSource"></property>
    </bean>
    <!--2:开启基于注解的事务控制模式；依赖tx名称空间  -->
    <tx:annotation-driven transaction-manager="tm"/>
    <!--3:给事务方法加注解@Transactional  -->
```

- 使用示例：

```java
    /**
     * 事务示例：购买图书
     * <p>
     * 注解 @Transactional 保证对数据库的操作要么全部完成，要么全部回滚
     *
     * @param username 顾客姓名
     * @param bookNo   图书编号
     */
    @Transactional
    public void checkout(String username, String bookNo) {
        // jdbc 减图书库存操作

        // jdbc 减顾客余额操作
    }
```
