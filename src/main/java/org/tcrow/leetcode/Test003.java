package org.tcrow.leetcode;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test003 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            System.out.println(lengthOfLongestSubstring(line));
        }
    }

    public static int lengthOfLongestSubstring(String line) {
        if (line == null || line.length() == 0) {
            return 0;
        }
        char[] chars = line.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < chars.length; j++) {
                char c = chars[j];
                if (!set.contains(c)) {
                    set.add(c);
                } else {
                    max = Math.max(max, set.size());
                    break;
                }
            }
            max = Math.max(max, set.size());
        }
        return max;
    }
}
