package org.tcrow.od;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 最小步骤数
 * 题目描述
 * 一个正整数数组 设为nums
 * 最大为100个成员
 * 求从第一个成员开始正好走到数组最后一个成员所使用的最小步骤数
 * 要求：
 * <p>
 * 第一步 必须从第一元素起，且1 <= 第一步步长 < len/2 (len为数组长度)
 * 从第二步开始只能以所在成员的数字走相应的步数，不能多不能少，
 * 如果目标不可达返回-1
 * 只输出最小的步骤数量
 * 只能向数组的尾部走不能向回走
 * 输入描述
 * 一正整数数组 空格分割
 * 数组长度<100
 * <p>
 * 输出描述
 * 正整数 最小步数
 * 不存在输出-1
 * <p>
 * 示例一
 * 输入
 * 7 5 9 4 2 6 8 3 5 4 3 9
 * 输出
 * 2
 * 说明
 * 第一个可选步长选择2
 * 从第一个成员7开始走两步到9
 * 第二步：从9经过9个成员到最后
 * <p>
 * 示例二
 * 输入
 * 1 2 3 7 1 5 9 3 2 1
 * 输出
 * -1
 */
public class Test053 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        List<Integer> list = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int step = Integer.MAX_VALUE;
        for (int i = 1; i < list.size() / 2; i++) {
            int num = getStep(list, i, 1);
            if (num > 0) {
                step = Math.min(step, num);
            }
        }
        if (step == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(step);
    }

    private static int getStep(List<Integer> list, int start, int step) {
        if (start == list.size() - 1) {
            return step;
        }
        if (start > list.size() - 1) {
            return -1;
        }
        return getStep(list, start + list.get(start), step + 1);
    }
}
