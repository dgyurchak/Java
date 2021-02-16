//https://progcont.hu/progcont/100317/?pid=201428

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Map<String, List<Integer>>> map = new TreeMap<>();
        String[] data;

        while (sc.hasNextLine()) {
            data = sc.nextLine().split(";");
            for (int i = 2; i < data.length; i++) {
                Map<String, List<Integer>> value = map.get(data[i]);
                if (value == null) {
                    value = new TreeMap<>();
                    map.put(data[i], value);
                }
                List<Integer> sum = value.get(data[0]);
                if (sum == null) {
                    sum = new ArrayList<>();
                   value.put(data[0], sum);
                }
                sum.add(Integer.parseInt(data[1]));
            }
        }

        for (Map.Entry<String, Map<String, List<Integer>>> entry: map.entrySet()) {
            for (Map.Entry<String, List<Integer>> value: entry.getValue().entrySet()) {
                int tmp = 0;
                for (int i: value.getValue()) {
                    tmp += i;
                }
                System.out.println(entry.getKey() + " => " + value.getKey() + ": " + tmp);
            }
        }
    }
}
