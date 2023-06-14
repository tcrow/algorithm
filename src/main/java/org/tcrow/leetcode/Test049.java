package org.tcrow.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Test049 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            List<List<String>> lists = groupAnagrams(line.replaceAll(" ","").split(","));
            System.out.println(lists);
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = sort(str);
            List<String> list;
            if (map.containsKey(key)) {
                list = map.get(key);
            } else {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(key, list);
        }
        return map.values().stream().collect(Collectors.toList());
    }

    private static String sort(String str) {
        List<String> list = Arrays.stream(str.split("")).sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
