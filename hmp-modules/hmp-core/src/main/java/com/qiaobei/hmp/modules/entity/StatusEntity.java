package com.qiaobei.hmp.modules.entity;

import com.google.common.base.Objects;
import com.qiaobei.hmp.modules.support.OrdinalEnum;
import org.springside.modules.utils.MetaData;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class StatusEntity extends IdEntity {

    protected Status status = Status.Normal;

    public StatusEntity() {
    }


    public StatusEntity(Long id) {
        super(id);
    }

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusEntity)) return false;
        if (!super.equals(o)) return false;
        StatusEntity that = (StatusEntity) o;
        return Objects.equal(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), status);
    }

    public enum Status implements OrdinalEnum {
        @MetaData("正常")
        Normal {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "正常";
            }
        },//0
        @MetaData("禁止")
        Disabled {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "禁止";
            }
        },//1
        @MetaData("锁住")
        Locked {
            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public String getName() {
                return "锁住";
            }
        },//2
        @MetaData("取消")
        Canceled {
            @Override
            public Integer getValue() {
                return 3;
            }

            @Override
            public String getName() {
                return "取消";
            }
        },//3
        @MetaData("删除")
        Removed {
            @Override
            public Integer getValue() {
                return 3;
            }

            @Override
            public String getName() {
                return "删除";
            }
        },//4
        @MetaData("存档")
        Archived {
            @Override
            public Integer getValue() {
                return 5;
            }

            @Override
            public String getName() {
                return "存档";
            }
        },//5
        @MetaData("提交")
        Committed {
            @Override
            public Integer getValue() {
                return 6;
            }

            @Override
            public String getName() {
                return "提交";
            }
        },//6
        @MetaData("应用")
        Applied {
            @Override
            public Integer getValue() {
                return 7;
            }

            @Override
            public String getName() {
                return "应用";
            }
        },//7
        @MetaData("验证")
        Verified {
            @Override
            public Integer getValue() {
                return 8;
            }

            @Override
            public String getName() {
                return "验证";
            }
        },//8
        @MetaData("未激活")
        Unactivated {
            @Override
            public Integer getValue() {
                return 9;
            }

            @Override
            public String getName() {
                return "未激活";
            }
        },//9
        @MetaData("使用中")
        Used {
            @Override
            public Integer getValue() {
                return 10;
            }

            @Override
            public String getName() {
                return "使用中";
            }
        },//10
        @MetaData("已经阅读")
        Readed {
            @Override
            public Integer getValue() {
                return 11;
            }

            @Override
            public String getName() {
                return "已经阅读";
            }
        },//11
        @MetaData("临时处方患者")
        Tmp {
            @Override
            public Integer getValue() {
                return 12;
            }

            @Override
            public String getName() {
                return "临时处方患者";
            }
        },//12
        @MetaData("爽约")
        NotSingIn {
            @Override
            public Integer getValue() {
                return 13;
            }

            @Override
            public String getName() {
                return "爽约";
            }
        },//13
        @MetaData("未收费")
        UN_CHARGE {
            @Override
            public Integer getValue() {
                return 14;
            }

            @Override
            public String getName() {
                return "未收费";
            }
        },//14
        @MetaData("已经收费")
        CHARGE {
            @Override
            public Integer getValue() {
                return 15;
            }

            @Override
            public String getName() {
                return "已经收费";
            }
        },//15
        @MetaData("挂起")
        HANG_UP {
            @Override
            public Integer getValue() {
                return 16;
            }

            @Override
            public String getName() {
                return "挂起";
            }
        },//16
        @MetaData("已经发药")
        Have_Dispensing {
            @Override
            public Integer getValue() {
                return 17;
            }

            @Override
            public String getName() {
                return "已经发药";
            }
        }, //17
        @MetaData("已经退药")
        Have_Dispensing_Back {
            @Override
            public Integer getValue() {
                return 18;
            }

            @Override
            public String getName() {
                return "已经退药";
            }
        }, //18
        @MetaData("已经检查或检验")
        Have_Exam_Or_Lab {
            @Override
            public Integer getValue() {
                return 19;
            }

            @Override
            public String getName() {
                return "已经检查或检验";
            }
        }, //19,
        @MetaData("病人被医生看过")
        DoctorHaveSee {
            @Override
            public Integer getValue() {
                return 20;
            }

            @Override
            public String getName() {
                return "病人被医生看过";
            }
        },  //20
        @MetaData("退钱成功")
        Back_Money_Success {
            @Override
            public Integer getValue() {
                return 21;
            }

            @Override
            public String getName() {
                return "退钱成功";
            }
        }//21

    }


}
