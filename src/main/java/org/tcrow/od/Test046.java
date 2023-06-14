package org.tcrow.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 乘积最大值
 * 题目描述
 * 给定一个元素类型为小写字符串的数组
 * 请计算两个没有相同字符的元素长度乘积的最大值
 * 如果没有符合条件的两个元素返回0
 * <p>
 * 输入描述
 * 输入为一个半角逗号分割的小写字符串数组
 * 2 <= 数组长度 <= 100
 * 0 < 字符串长度 <= 50
 * <p>
 * 输出描述
 * 两个没有相同字符的元素长度乘积的最大值
 * <p>
 * 示例一
 * 输入
 * iwdvpbn,hk,iuop,iikd,kadgpf
 * 输出
 * 14
 * 说明
 * 数组中有5个元组 第一个和第二个元素没有相同字符
 * 满足条件 输出7 * 2 = 14
 */
public class Test046 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        List<String> list = Arrays.stream(line.split(",")).collect(Collectors.toList());
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                max = Math.max(check(list.get(i), list.get(j)), max);
            }
        }
        System.out.println(max);
    }

    private static int check(String str1, String str2) {
        Set<Character> set = new HashSet<>();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (char c : chars1) {
            set.add(c);
        }
        for (char c : chars2) {
            set.add(c);
        }
        if (set.size() == (chars1.length + chars2.length)) {
            return chars1.length * chars2.length;
        }
        return 0;
    }
}
