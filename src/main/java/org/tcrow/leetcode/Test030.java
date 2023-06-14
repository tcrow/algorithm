package org.tcrow.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Test030 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            String line2 = scanner.nextLine();
            List<Integer> list = findSubstring(line, line2.replaceAll(" ", "").split(","));
            System.out.println(list);
        }
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> allWords = new HashMap<>();
        int len = words[0].length();
        for (String word : words) {
            int value = allWords.getOrDefault(word, 0);
            allWords.put(word, value + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length() - words.length * len + 1; i++) {
            HashMap<String, Integer> hasMap = new HashMap<>();
            for (int j = 0; j < words.length; j++) {
                String key = s.substring(i + j * len, i + j * len + len);
                if (!allWords.containsKey(key)) {
                    break;
                }
                int value = hasMap.getOrDefault(key, 0);
                if (allWords.getOrDefault(key, 0) < value + 1) {
                    break;
                }
                hasMap.put(key, value + 1);
            }
            if (allWords.size() != hasMap.size()) {
                continue;
            }
            boolean flag = true;
            for (String s1 : hasMap.keySet()) {
                int a = hasMap.get(s1);
                int b = allWords.get(s1);
                if (a != b) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(i);
            }
        }
        return result;
    }

}
