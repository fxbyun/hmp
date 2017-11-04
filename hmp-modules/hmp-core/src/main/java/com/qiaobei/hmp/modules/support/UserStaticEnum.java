package com.qiaobei.hmp.modules.support;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public enum UserStaticEnum implements OrdinalEnum {

    NEW{

        public Integer getValue() {
            // TODO Auto-generated method stub
            return 1;
        }

        public String getName() {
            // TODO Auto-generated method stub
            return "新用户注册";
        }

    },

    OLD{

        public Integer getValue() {
            // TODO Auto-generated method stub
            return 2;
        }

        public String getName() {
            // TODO Auto-generated method stub
            return "该用户已注册过";
        }

    },

    ERROR{
        public Integer getValue() {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getName() {
            // TODO Auto-generated method stub
            return "验证码错误";
        }
    }
}
