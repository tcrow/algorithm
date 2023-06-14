package org.tcrow.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第k长子串
 * 题目描述
 * 给定一个字符串
 * 只包含大写字母
 * 求在包含同一字母的子串中
 * 长度第K长的子串
 * 相同字母只取最长的子串
 * <p>
 * 输入描述
 * 第一行 一个子串 1 < len <= 100
 * 只包含大写字母
 * 第二行为k的值
 * <p>
 * 输出描述
 * 输出连续出现次数第k多的字母的次数
 * 如果子串中只包含同一字母的子串数小于k`
 * 则输出-1
 * <p>
 * 示例一
 * 输入
 * AABAAA
 * 2
 * 输出
 * 1
 * 说明
 * 同一字母连续出现最多的A，3次
 * 第二多2次，但A出现连续3次
 * <p>
 * 示例二
 * 输入
 * AAAAHHHBBCDHHHH
 * 3
 * 输出
 * 2
 */
public class Test015 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            int k = Integer.parseInt(scanner.nextLine());
            solution(k, input);
        }
    }

    private static void solution(int k, String input) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int value = 1;
            int j = i + 1;
            while (j < chars.length && chars[j] == chars[j - 1]) {
                value++;
                j++;
            }
            if (map.containsKey(chars[i])) {
                map.put(chars[i], Math.max(map.get(chars[i]), value));
            } else {
                map.put(chars[i], value);
            }
            // 循环会导致i++，所以先减1
            i = j - 1;
        }
        List<Integer> collect = map.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (k > collect.size()) {
            System.out.println(-1);
        } else {
            System.out.println(collect.get(k - 1));
        }
    }
}
