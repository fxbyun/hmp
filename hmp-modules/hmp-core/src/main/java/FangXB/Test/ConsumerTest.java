package FangXB.Test;

import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/2/5 0005.
 */
public class ConsumerTest {
    public static void main(String[] args) {
        System.out.println(maker(Employee::new));
    }

    private static Employee maker(Supplier<Employee> fx) {
        return fx.get();
    }
}

class Employee {
    public String test = "123123123";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "A EMPLOYEE";
    }
}
