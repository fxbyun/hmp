package FangXB.Test;

import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/2/5 0005.
 */
public class BinaryOperator {
    public static void main(String[] args) {
        Supplier<String> supp = () -> {
            return "Supplier";
        };
        System.out.println(supp.get());
    }

}
