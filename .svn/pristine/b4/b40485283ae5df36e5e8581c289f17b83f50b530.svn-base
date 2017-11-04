package FangXB.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortByValueExample {

    public static void main(String[] argv) {
        Map<Test, Long> unsortMap = new HashMap<>();
        unsortMap.put(new Test(1L), 10L);
        unsortMap.put(new Test(12L), 5L);
        unsortMap.put(new Test(13L), 6L);
        unsortMap.put(new Test(14L), 20L);
        /*unsortMap.put(new Medicine(), 1L);
        unsortMap.put(new Medicine(), 7L);
        unsortMap.put(new Medicine(), 8L);
        unsortMap.put(new Medicine(), 99L);*/
        /*unsortMap.put("j", 50L);
        unsortMap.put("m", 2L);
        unsortMap.put("f", 9L);*/

        System.out.println("Original...");
        System.out.println(unsortMap);

        Map<Test, Long> result = new LinkedHashMap<>();

        //sort by value, and reserve, 10,9,8,7,6...
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<Test, Long>comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);

    }


}
