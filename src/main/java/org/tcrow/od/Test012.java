package org.tcrow.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目0012-快递货车
 * 页面内容
 * 讨论
 * 最后编辑
 * Amos
 * 07/11/2022
 * 快递货车
 * 题目描述
 * 一辆运送快递的货车，
 * 运送的快递放在大小不等的长方体快递盒中，
 * 为了能够装载更多的快递同时不能让货车超载，
 * 需要计算最多能装多少个快递。
 * 注：快递的体积不受限制。
 * 快递数最多1000个，货车载重最大50000。
 * <p>
 * 输入描述
 * 第一行输入每个快递的重量
 * 用英文逗号隔开
 * 如 5,10,2,11
 * 第二行输入货车的载重量
 * 如 20
 * <p>
 * 输出描述
 * 输出最多能装多少个快递
 * 如 3
 * <p>
 * 示例一
 * 输入
 * 5,10,2,11
 * 20
 * 输出
 * 3
 */
public class Test012 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            int k = scanner.nextInt();
            solution(input, k);
        }
    }

    private static void solution(String input, int k) {
        String[] split = input.split(",");
        int[] array = new int[split.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(array);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum < k) {
                continue;
            }
            System.out.println(i);
            return;
        }
    }
}
