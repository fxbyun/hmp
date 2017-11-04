package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJDoctorExamLab is a Querydsl query type for JDoctorExamLab
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJDoctorExamLab extends EntityPathBase<JDoctorExamLab> {

    private static final long serialVersionUID = -873774549L;

    public static final QJDoctorExamLab jDoctorExamLab = new QJDoctorExamLab("jDoctorExamLab");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath classAdviceDictName = createString("classAdviceDictName");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final NumberPath<Long> examLabId = createNumber("examLabId", Long.class);

    public final StringPath examLabName = createString("examLabName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Long> subId = createNumber("subId", Long.class);

    public final NumberPath<Long> subTwoId = createNumber("subTwoId", Long.class);

    public final EnumPath<JClassAdviceDict.Advice_Type> type = createEnum("type", JClassAdviceDict.Advice_Type.class);

    public QJDoctorExamLab(String variable) {
        super(JDoctorExamLab.class, forVariable(variable));
    }

    public QJDoctorExamLab(Path<? extends JDoctorExamLab> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJDoctorExamLab(PathMetadata metadata) {
        super(JDoctorExamLab.class, metadata);
    }

}

