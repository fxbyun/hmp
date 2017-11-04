package com.qiaobei.hmp.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;


/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * <p>
 * Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类.
 * 相比Spring原基类名字更短并保存了dataSource变量.
 * 子类需要定义applicationContext文件的位置, 如:
 *
 * @author 凉生
 *         Date 2017/2/10 0010.
 *         Time 9:37.
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 */

@ActiveProfiles(Profiles.PRODUCTION)
public class SpringTransactionalTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    private DataSource dataSource;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }
}
