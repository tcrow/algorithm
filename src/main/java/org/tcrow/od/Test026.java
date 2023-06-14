package org.tcrow.od;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 题目名称
 * 题目描述
 * 给定一个数组，编写一个函数，
 * 计算他的最大N个数和最小N个数的和，
 * 需要对数组进行去重。
 * <p>
 * 输入描述
 * 第一行输入M，M表示数组大小
 * 第二行输入M个数，表示数组内容
 * 第三行输入N表示需要计算的最大最小N的个数
 * <p>
 * 输出描述
 * 输出最大N个数和最小N个数的和
 * <p>
 * 示例一
 * 输入
 * 5
 * 95 88 83 64 100
 * 2
 * 输出
 * 342
 * ¶说明
 * 最大2个数[100 95]最小2个数[83 64]
 * 输出342
 * <p>
 * 示例一
 * 输入
 * 5
 * 3 2 3 4 2
 * 2
 * 输出
 * -1
 * 说明
 * 最大2个数是[4 3]最小2个数是[3 2]
 * 有重叠输出为-1
 */
public class Test026 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String input = scanner.nextLine();
            int N = Integer.parseInt(scanner.nextLine());
            solution(input, N);
        }
    }

    private static void solution(String input, int N) {
        String[] split = input.split(" ");
        Set<Integer> set = new TreeSet<>();
        for (String str : split) {
            set.add(Integer.parseInt(str));
        }

        if (N * 2 > set.size()) {
            System.out.println(-1);
            return;
        }

        int sum = 0;
        List<Integer> collect = set.stream().collect(Collectors.toList());
        for (int i = 0; i < N; i++) {
            sum += collect.get(i);
        }

        for (int i = 0; i < N; i++) {
            sum += collect.get(collect.size() - i - 1);
        }

        System.out.println(sum);
    }
}
