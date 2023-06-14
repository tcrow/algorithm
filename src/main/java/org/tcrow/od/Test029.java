package org.tcrow.od;

import java.util.*;

/**
 * 整数分解
 * 题目描述
 * 一个整数可以由连续的自然数之和来表示。
 * 给定一个整数，计算该整数有几种连续自然数之和的表达式，
 * 并打印出每一种表达式。
 * <p>
 * 输入描述
 * 一个目标整数t，1 <= t <= 1000
 * <p>
 * 输出描述
 * 该整数的所有表达式和表达式的个数
 * 如果有多种表达式，自然数个数最少的表达式优先输出
 * 每个表达式中按自然数递增输出
 * 具体的格式参见样例
 * 在每个测试数据结束时，输出一行Result:X
 * 其中X是最终的表达式个数
 * 示例一
 * 输入
 * 9
 * 输出
 * 9=9
 * 9=4+5
 * 9=2+3+4
 * Result:3
 * 说明
 * 整数9有三种表达方法
 * <p>
 * 示例二
 * 输入
 * 10
 * 输出
 * 10=10
 * 10=1+2+3+4
 * Result:2
 */
public class Test029 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            solution(k);
        }
    }

    private static List<Integer[]> list = new ArrayList<>();
    private static Set<String> set = new HashSet<>();

    private static void solution(int k) {
        System.out.println(k + "=" + k);
        for (int i = 2; i < k; i++) {
            process(k, i);
        }
        list.sort(Comparator.comparingInt(o -> o.length));

        for (Integer[] integers : list) {
            System.out.print(k + "=");
            for (int i = 0; i < integers.length; i++) {
                if (i != integers.length - 1) {
                    System.out.print(integers[i] + "+");
                } else {
                    System.out.print(integers[i]);
                }
            }
            System.out.println();
        }
        System.out.println("Result:" + list.size());
    }

    private static void process(int k, int num) {
        for (int i = 1; i <= k; i++) {
            int sum = 0;
            for (int j = i; j < num + i; j++) {
                sum += j;
            }
            if (sum == k) {
                getArray(i, num + i - 1);
                return;
            }
        }
    }

    private static void getArray(int start, int end) {
        if (set.contains(start + " " + end)) {
            return;
        }
        Integer[] array = new Integer[end - start + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + start;
        }
        list.add(array);
        set.add(start + " " + end);
    }
}
