package org.tcrow.od;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 乱序整数序列两数之和绝对值最小
 * 题目描述
 * 给定一个随机的整数数组(可能存在正整数和负整数)nums,
 * 请你在该数组中找出两个数，其和的绝对值(|nums[x]+nums[y]|)为最小值
 * 并返回这两个数(按从小到大返回)以及绝对值。
 * 每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 输入描述
 * 一个通过空格空格分割的有序整数序列字符串，最多1000个整数，
 * 且整数数值范围是[-65535,65535]
 * <p>
 * 输出描述
 * 两个数和两数之和绝对值
 * <p>
 * 示例一
 * 输入
 * -1 -3 7 5 11 15
 * 输出
 * -3 5 2
 * 说明
 * 因为|nums[0]+nums[2]|=|-3+5|=2最小，
 * 所以返回-3 5 2
 */
public class Test023 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            solution(input);
        }
    }

    private static void solution(String input) {
        String[] strings = input.split(" ");
        List<Integer> list = Arrays.stream(strings)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int min = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int abs = Math.abs(list.get(i) + list.get(j));
                if (min > abs) {
                    min = abs;
                    res[0] = Math.min(list.get(i), list.get(j));
                    res[1] = Math.max(list.get(i), list.get(j));
                }
            }
        }
        System.out.println(res[0] + " " + res[1] + " " + min);
    }
}
