/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.qiaobei.hmp.test.repository;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.service.MedicineCountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import org.springside.modules.test.spring.Profiles;

@DirtiesContext
@ContextConfiguration(locations = {"/applicationContext.xml"})
//@ActiveProfiles(Profiles.UNIT_TEST)
public class DynamicSpecificationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private MedicineCountService medicineCountService;

    @Test
    public void findMedCountTest() {
        List<MedicineCount> medicineCountList = medicineCountService.getMedCountListByType(Medicine.Type.Western);
        System.out.println(medicineCountList.size());
    }

    @Test
    public void fineUserByFilter() {
        // EQ
        SearchFilter filter = new SearchFilter("name", Operator.EQ, "管理员");
        List<Doctor> users = doctorDao
                .findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(1);

        // LIKE
        filter = new SearchFilter("loginName", Operator.LIKE, "min");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(1);

        // GT
        filter = new SearchFilter("id", Operator.GT, "1");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(5);

        filter = new SearchFilter("id", Operator.GT, "6");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).isEmpty();

        // GTE
        filter = new SearchFilter("id", Operator.GTE, "1");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(6);

        filter = new SearchFilter("id", Operator.GTE, "6");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(1);

        // LT
        filter = new SearchFilter("id", Operator.LT, "6");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(5);

        filter = new SearchFilter("id", Operator.LT, "1");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).isEmpty();

        // LTE
        filter = new SearchFilter("id", Operator.LTE, "6");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(6);

        filter = new SearchFilter("id", Operator.LTE, "1");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(1);

        // Empty filters, select all
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(new ArrayList<SearchFilter>(), Doctor.class));
        assertThat(users).hasSize(6);

        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(null, Doctor.class));
        assertThat(users).hasSize(6);

        // AND 2 Conditions
        SearchFilter filter1 = new SearchFilter("name", Operator.EQ, "管理员");
        SearchFilter filter2 = new SearchFilter("loginName", Operator.LIKE, "min");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter1, filter2), Doctor
                .class));
        assertThat(users).hasSize(1);

        filter1 = new SearchFilter("name", Operator.EQ, "管理员");
        filter2 = new SearchFilter("loginName", Operator.LIKE, "user");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter1, filter2), Doctor
                .class));
        assertThat(users).isEmpty();

        // 2 conditions on same field
        filter1 = new SearchFilter("id", Operator.GTE, "1");
        filter2 = new SearchFilter("id", Operator.LTE, "6");

        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter1, filter2), Doctor
                .class));
        assertThat(users).hasSize(6);

        // Nest Attribute
        filter = new SearchFilter("team.id", Operator.EQ, "1");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).hasSize(6);

        filter = new SearchFilter("team.id", Operator.EQ, "10");
        users = doctorDao.findAll(DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter), Doctor.class));
        assertThat(users).isEmpty();
    }
}
