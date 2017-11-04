package com.qiaobei.hmp.test.test;

import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.test.repository.JpaMappingTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 *
 * @author 凉生
 *         Date 2017/2/10 0010.
 *         Time 9:39.
 */


@ContextConfiguration(locations = {"classpath:applicationContext-junit.xml"})
//有不用@RunWith也可以用的，自己没有测试
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
//如果是true不会改变数据库数据，如果是false会改变数据
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DingTest extends SpringTransactionalTestCase {
    private static Logger logger = LoggerFactory.getLogger(JpaMappingTest.class);
    @Autowired
    private DoctorService doctorService;
    @PersistenceContext
    private EntityManager em;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void test() {
        System.out.println(doctorService + "------");
    }

    @Test
    public void allClassMapping() throws Exception {
        Metamodel model = em.getEntityManagerFactory().getMetamodel();

        assertThat(model.getEntities()).as("No entity mapping found").isNotEmpty();

        for (EntityType entityType : model.getEntities()) {
            String entityName = entityType.getName();
//            em.createQuery("select o from " + entityName + " o").getResultList();
            logger.info("ok: " + entityName);
        }
    }

}
