package org.tcrow.od;

import java.util.*;

/**
 * 删除最少字符
 * 题目描述
 * 删除字符串中出现次数最少的字符
 * 如果多个字符出现次数一样则都删除
 * <p>
 * 输入描述
 * 输入只包含小写字母
 * <p>
 * 输出描述
 * 输出删除后剩余的字符
 * 若删除后字符串长度为0，则输出empty
 * <p>
 * 示例一
 * 输入
 * abcdd
 * 输出
 * dd
 * 示例二
 * 输入
 * aabbccdd
 * 输出
 * empty
 */
public class Test017 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            solution(input);
        }
    }

    private static void solution(String input) {
        char[] chars = input.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer value : map.values()) {
            min = Math.min(value, min);
        }
        List<Character> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == min) {
                list.add(entry.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (!list.contains(input.charAt(i))) {
                sb.append(input.charAt(i));
            }
        }
        if (sb.length() == 0) {
            System.out.println("empty");
        } else {
            System.out.println(sb);
        }
    }
}
