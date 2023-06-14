package org.tcrow.od;

import java.util.Scanner;

/**
 * 矩阵最值
 * 题目描述
 * 给定一个仅包含0和1的n*n二维矩阵
 * 请计算二维矩阵的最大值
 * 计算规则如下
 * <p>
 * 每行元素按下标顺序组成一个二进制数(下标越大约排在低位)，
 * 二进制数的值就是该行的值，矩阵各行之和为矩阵的值
 * 允许通过向左或向右整体循环移动每个元素来改变元素在行中的位置
 * 比如
 * [1,0,1,1,1]向右整体循环移动两位[1,1,1,0,1]
 * 二进制数为11101值为29
 * [1,0,1,1,1]向左整体循环移动两位[1,1,1,1,0]
 * 二进制数为11110值为30
 * 输入描述
 * 数据的第一行为正整数，记录了N的大小，0 < N <= 20
 * 输入的第2到n+1行为二维矩阵信息，行内元素边角逗号分割
 * 输出描述
 * 矩阵的最大值
 * <p>
 * 示例一
 * 输入
 * 5
 * 1,0,0,0,1
 * 0,0,0,1,1
 * 0,1,0,1,0
 * 1,0,0,1,1
 * 1,0,1,0,1
 * 输出
 * 122
 * 说明
 * 第一行向右整体循环移动一位，得到最大值 11000 24
 * 11000
 * 10100
 * 11100
 * 10110
 * 因此最大122
 */
public class Test028 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String[] inputs = new String[k];
            for (int i = 0; i < k; i++) {
                inputs[i] = scanner.nextLine();
            }
            solution(inputs);
        }
    }

    private static void solution(String[] inputs) {
        int sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += getMax(inputs[i]);
        }
        System.out.println(sum);
    }

    private static int getMax(String input) {
        input = input.replaceAll(",", "");
        int len = input.length();
        int bin = Integer.parseInt(input, 2);
        int max = bin;
        for (int i = 0; i < len; i++) {
            input = left(input);
            max = Math.max(max, Integer.parseInt(input, 2));
        }
        return max;
    }

    private static String left(String input) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i + 1 != input.length()) {
                res.append(input.charAt(i + 1));
            } else {
                res.append(input.charAt(0));
            }
        }
        return res.toString();
    }
}
