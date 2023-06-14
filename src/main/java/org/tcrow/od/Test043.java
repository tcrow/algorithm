package org.tcrow.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目名称
 * 题目描述
 * 给定一个正整数数组
 * 检查数组中是否存在满足规则的数组组合
 * 规则：
 * A = B + 2C
 * <p>
 * 输入描述
 * 第一行输出数组的元素个数
 * 接下来一行输出所有数组元素，用空格隔开
 * <p>
 * 输出描述
 * 如果存在满足要求的数
 * 在同一行里依次输出规则里A B C的取值，用空格隔开
 * 如果不存在输出0
 * <p>
 * 示例一
 * 输入
 * 4
 * 2 7 3 0
 * 输出
 * 7 3 2
 * 说明
 * 7 = 3 + 2 * 2
 * <p>
 * 示例一
 * 输入
 * 3
 * 1 1 1
 * 输出
 * 0
 * 说明
 * 说明找不到满足条件的组合
 * <p>
 * 备注
 * 数组长度在3 ~ 100之间
 * 数组成员为0 ~ 65535
 * 数组成员可以重复
 * 但每个成员只能在结果算式中使用一次
 * 如 数组成员为 [0,0,1,5]
 * 0出现两次允许，但结果0=0+2*0不允许 因为算式中使用了3个0
 * 用例保证每组数字里最多只有一组符合要求的解
 */
public class Test043 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            solution(nums);
        }
    }

    private static void solution(int[] nums) {

        int[] ints = Arrays.stream(nums)
                .distinct()
                .sorted()
                .toArray();

        String res = "0";

        for (int a = ints.length - 1; a >= 0; a--) {
            for (int b = 0; b < a; b++) {
                for (int c = 0; c < a; c++) {
                    int A = ints[a];
                    int B = ints[b];
                    int C = ints[c];
                    if (A == B + 2 * C) {
                        res = A + " " + B + " " + C;
                    }
                }
            }
        }

        System.out.println(res);
    }
}
