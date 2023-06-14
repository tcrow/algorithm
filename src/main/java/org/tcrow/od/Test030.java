package org.tcrow.od;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 滑动窗口
 * 题目描述
 * 有一个N个整数的数组，和一个长度为M的窗口。
 * 窗口从数组内的第一个数开始滑动，直到窗口不能滑动为止。
 * 每次滑动产生一个窗口，和窗口内所有数的和，
 * 求窗口滑动产生的所有窗口和的最大值
 * <p>
 * 输入描述
 * 第一行输入一个正整数N，表示整数个数0 < N < 100000
 * <p>
 * 第二行输入N个整数，整数取值范围[-100,100]
 * <p>
 * 第三行输入正整数M，M代表窗口的大小，M <= N <= 100000
 * <p>
 * 输出描述
 * 窗口滑动产生所有窗口和的最大值
 * <p>
 * 示例一
 * 输入
 * 6
 * 12 10 20 30 15 23
 * 3
 * 输出
 * 68
 */
public class Test030 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String input = scanner.nextLine();
            int m = Integer.parseInt(scanner.nextLine());
            solution(input, m);
        }
    }

    private static void solution(String input, int m) {
        List<Integer> collect = Arrays.stream(input.split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
        long max = Long.MIN_VALUE;

        for (int i = 0; i < collect.size() - m + 1; i++) {
            int sum = 0;
            for (int j = i; j < i + m; j++) {
                sum += collect.get(j);
            }
            max = Math.max(sum, max);
        }

        System.out.println(max);
    }
}
