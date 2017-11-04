package FangXB.Test;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */
public class CollectionUtil {
    public static Integer TestAdd(Integer aa) {
        aa++;
        System.out.println(aa);
        return aa;
    }

    public static Integer TestAdd2(int aa) {
        aa++;
        System.out.println(aa);
        return aa;
    }
    public static void main(String[] args) {
        List list = Lists.newArrayList();
        list.add("12");
        Integer test = 12;
        int test2 = 15;
        TestAdd(test);
        TestAdd2(test2);
        System.out.println(test);
        System.out.println(test2);
    }
}
