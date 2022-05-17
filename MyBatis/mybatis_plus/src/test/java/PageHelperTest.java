import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.knight.bean.Cat;
import com.knight.dao.CatMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@DisplayName("分页测试")
public class PageHelperTest {

    SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @DisplayName("分页测试准备——批量插入")
    @Test
    void test1() {

        SqlSession session = sqlSessionFactory.openSession();
        CatMapper mapper = session.getMapper(CatMapper.class);

        List<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Cat cat = new Cat();
            cat.setCname(UUID.randomUUID().toString().substring(0, 5) + "猫");
            cat.setcGender((int) (Math.random() * 100) / 50);
            cat.setCage((int) (Math.random() * 100) + 1);
            cats.add(cat);
        }
        mapper.insertBatch(cats);
        session.commit();
        session.close();

        System.out.println("批量插入完毕");
    }


    @DisplayName("分页测试执行")
    @Test
    void test2() {

        SqlSession session = sqlSessionFactory.openSession();
        CatMapper mapper = session.getMapper(CatMapper.class);

        // 紧跟它的查询就是分页查询(页码，页大小)
        PageHelper.startPage(1, 5);
        List<Cat> cats = mapper.selectAll();

        session.commit();
        session.close();
        for (Cat cat : cats) {
            System.out.println(cat);
        }

        // 将查询到的结果放入PageInfo中，扩展其属性（第二个参数可选，表示分页栏下面一共展示多少页码，如下面为6页）
        // 首页 上一页 2 3 4 5 6 7 下一页 末页
        PageInfo<Cat> page = new PageInfo<>(cats, 6);
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("总页码：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页有几条记录：" + page.getSize());
        System.out.println("当前页的PageSize：" + page.getPageSize());
        System.out.println("前一页：" + page.getPrePage());
        System.out.println("下一页：" + page.getNextPage());
        System.out.println("当前页面第一个元素在数据库中的行号：" + page.getStartRow());
        System.out.println("当前页面最后一个元素在数据库中的行号：" + page.getEndRow());
        System.out.println("是否有前一页：" + page.isHasPreviousPage());
        System.out.println("是否有下一页：" + page.isHasNextPage());
        System.out.println("查询结果：" + page.getList());
        System.out.println("所有导航页号：");
        for (int i : page.getNavigatepageNums()) {
            System.out.println(i);
        }
    }
}
