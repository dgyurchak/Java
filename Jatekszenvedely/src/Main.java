//https://progcont.hu/progcont/100335/?pid=201468

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, List<Integer>> map = new TreeMap<>();
        Map<Integer, List<String>> map1 = new TreeMap<>(
                Comparator.reverseOrder()
        );
        Scanner sc = new Scanner(System.in);
        String[] data;

        while (sc.hasNextLine()) {
            data = sc.nextLine().split(":");
            List<Integer> value = map.get(data[0]);
            if (value == null) {
                value = new ArrayList<>();
                map.put(data[0], value);
            }
            if (map.containsKey(data[0])) {
                value.add(Integer.parseInt(data[1]));
            }
        }

        for (Map.Entry<String, List<Integer>> entry: map.entrySet()) {
            int tmp = 0;
            for (int num: entry.getValue()) {
                tmp += num;
            }
            List<String> value = map1.get(tmp);
            if (value == null) {
                value = new ArrayList<>();
                map1.put(tmp, value);
            }
            if (map1.containsKey(tmp)) {
                value.add(entry.getKey());
            }
        }



        for (Map.Entry<Integer, List<String>> entry: map1.entrySet()) {
            StringJoiner sj = new StringJoiner(",");
            for (String name : entry.getValue()) {
                sj.add(name);
            }
            System.out.println(entry.getKey() + ":" + sj.toString());
        }
    }
}
