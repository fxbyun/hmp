package com.qiaobei.hmp.test.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * 子类需要定义applicationContext文件的位置,如:
 *
 * @author 凉生
 *         Date 2017/2/10 0010.
 *         Time 9:36.
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" })
 */

@ActiveProfiles(Profiles.PRODUCTION)
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {
}
