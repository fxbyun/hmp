package FangXB.Test;

import java.util.function.Function;

/**
 * Created by Administrator on 2017/2/5 0005.
 */
public class FunctionDemo {

    static void modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {

        int newValue = function.apply(valueToBeOperated);


        System.out.println(newValue);

    }

    public static void main(String[] args) {

        int incr = 20;
        int myNumber = 10;

        modifyTheValue(myNumber, val -> val + incr);

        myNumber = 15;
        modifyTheValue(myNumber, val -> val * 10);

        modifyTheValue(myNumber, val -> val - 100);

        modifyTheValue(myNumber, val -> "somestring".length() + val - 100);

    }


}
