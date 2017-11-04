package FangXB.Test;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class Test {
    public Long id;

    public Test(Long id) {
        this.id = id;
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024));
        System.out.println(Runtime.getRuntime().freeMemory() / (1024 * 1024));
        System.out.println(Runtime.getRuntime().totalMemory() / (1024 * 1024));
    }
}
