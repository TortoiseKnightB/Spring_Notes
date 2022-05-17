import com.knight.impl.MyMathCalculator;
import com.knight.inter.Calculator;
import com.knight.proxy.CalculatorProxy;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class AopTest {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @DisplayName("动态代理实现切面")
    @Test
    public void test1() {
        Calculator calculator = new MyMathCalculator();

        Calculator proxy = CalculatorProxy.getProxy(calculator);
        proxy.add(1, 2);

        // 代理对象和被代理对象唯一能产生的关联就是实现了同一个接口
        // class com.sun.proxy.$Proxy7 也实现了 Calculator 接口
        System.out.println(proxy.getClass());
        System.out.println(Arrays.asList(proxy.getClass().getInterfaces()));

    }

    @DisplayName("AOP切面初实现")
    @Test
    public void test2() {
        // 从ioc容器中拿到目标对象，如果用 类型方式获取，记得用 它的接口类型，不要用它本类
        // 实现了aop后获取的不再是本类对象，而是代理对象$Proxy,$Proxy也继承了Calculator，所以获取本类的话ioc.getBean(MyMathCalculator.class）会报错
        Calculator bean = ioc.getBean(Calculator.class);
        System.out.println(bean.getClass());
        System.out.println(bean.add(2, 1));
    }

    /**
     * 切入点表达式
     * <p>
     * 固定格式：execution(访问权限符 返回值类型 方法全类名(参数表))
     * <p>
     * 通配符 *：
     * <p>
     * 1）匹配一个或多个字符
     * <p>
     * 2）匹配任意一个参数
     * <p>
     * 3）只能匹配一层路径
     * <p>
     * 4）权限位置不能写 *，权限位置不写就行，public 可选
     * <p>
     * 通配符 ..：
     * <p>
     * 1）匹配任意多个参数，任意类型参数
     * <p>
     * 2）匹配任意多层路径
     * <p>
     * 两种写法：
     * <p>
     * 最模糊写法：execution(* *(..))，千万别写这种
     * <p>
     * 最精确写法：execution(public int com.knight.impl.MyMathCalculator.add(int, int))
     * <p>
     * 还可以加上 &&、||、!
     */
    @DisplayName("切入点表达式")
    @Test
    public void test3() {
        Calculator bean = ioc.getBean(Calculator.class);
        System.out.println(bean.getClass());
        System.out.println(bean.add(2, 1));
        System.out.println("***************************************");
        System.out.println(bean.div(1, 0));
    }
}