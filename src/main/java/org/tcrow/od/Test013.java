package org.tcrow.od;

import java.util.Scanner;

/**
 * 题目0013-航天器
 * <p>
 * 题目描述
 * 给航天器一侧加装长方形和正方形的太阳能板(图中的斜线区域);
 * 需要先安装两个支柱(图中的黑色竖条);
 * 再在支柱的中间部分固定太阳能板;
 * 但航天器不同位置的支柱长度不同;
 * 太阳能板的安装面积受限于最短一侧的那支支柱的长度;
 * <p>
 * 现提供一组整型数组的支柱高度数据;
 * 假设每个支柱间的距离相等为一个单位长度;
 * 计算如何选择两根支柱可以使太阳能板的面积最大;
 * <p>
 * 输入描述
 * 10,9,8,7,6,5,4,3,2,1
 * 注释，支柱至少有两根，最多10000根，能支持的高度范围1 ~ 10^9的整数
 * <p>
 * 柱子的高度是无序的
 * 例子中的递减是巧合
 * <p>
 * 输出描述
 * 可以支持的最大太阳板面积:(10m高支柱和5m高支柱之间)
 * 25
 * <p>
 * 示例一
 * 输入
 * 10,9,8,7,6,5,4,3,2,1
 * 输出
 * 25
 * 备注
 * 10米高支柱和5米高支柱之间宽度为5，高度取小的支柱高度也是5，面积为25
 * 任取其他两根支柱所能获得的面积都小于25 所以最大面积为25
 */
public class Test013 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            solution(input);
        }
    }

    private static void solution(String input) {
        String[] split = input.split(",");
        int[] array = new int[split.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int max = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                int h = Math.min(array[i], array[j]);
                int l = j - i;
                max = Math.max(max, h * l);
            }
        }

        System.out.println(max);
    }
}
