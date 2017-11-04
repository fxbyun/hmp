package com.qiaobei.hmp.test;

import com.qiaobei.hmp.modules.entity.QEmr;
import com.qiaobei.hmp.modules.entity.QEmrMedicine;
import com.qiaobei.hmp.modules.entity.QPatient;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/8 0008
 * Time 17:44
 */
public class QueryDslDemo {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void demo() {
        QEmr qEmr = QEmr.emr;
        //查询
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QPatient qPatient = QPatient.patient;
        List<Tuple> emrName = jpaQueryFactory.from(qEmr)
                .select(qPatient.name, qEmr.cost)
                .join(qEmr.patient, qPatient)
                .on(qEmr.patient.id.eq(qPatient.id))
                .where(qEmr.id.lt(10), qPatient.name.eq("王五"))
                .fetch();
        emrName.forEach(tuple -> System.out.println(tuple.get(qPatient.name)));

        QEmrMedicine qEmrMedicine = QEmrMedicine.emrMedicine;


        //更新
        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(entityManager, qEmr)
                .where(qEmr.id.eq(0L))
                .set(qEmr.cost, 66D);
        //删除
        JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager, qEmr)
                .where(qEmr.id.eq(0L));


    }
}
