package org.tcrow.od;

import java.util.Scanner;

/**
 * GPU调度
 * 题目描述
 * 为了充分发挥GPU算力，
 * 需要尽可能多的将任务交给GPU执行，
 * 现在有一个任务数组，
 * 数组元素表示在这1s内新增的任务个数，
 * 且每秒都有新增任务，
 * 假设GPU最多一次执行n个任务，
 * 一次执行耗时1s，
 * 在保证GPU不空闲的情况下，最少需要多长时间执行完成。
 * <p>
 * 输入描述
 * 第一个参数为GPU最多执行的任务个数
 * 取值范围1 ~ 10000
 * 第二个参数为任务数组的长度
 * 取值范围1 ~ 10000
 * 第三个参数为任务数组
 * 数字范围1 ~ 10000
 * <p>
 * 输出描述
 * 执行完所有任务需要多少秒
 * <p>
 * 示例一
 * 输入
 * 3
 * 5
 * 1 2 3 4 5
 * 输出
 * 6
 * 说明
 * 一次最多执行3个任务
 * 最少耗时6s
 * <p>
 * 示例二
 * 输入
 * 4
 * 5
 * 5 4 1 1 1
 * 输出
 * 5
 * 说明
 * 一次最多执行4个任务
 * 最少耗时5s
 */
public class Test055 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            int m = Integer.parseInt(scanner.nextLine());
            String line = scanner.nextLine();
            solution(k, line);
        }
    }

    private static void solution(int k, String line) {
        String[] strings = line.split(" ");
        int[] array = new int[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }

        int per = 0;
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            num++;
            per = (per + array[i] - k) < 0 ? 0 : per + array[i] - k;
        }

        while (per > 0) {
            num++;
            per = per - k;
        }
        System.out.println(num);
    }
}
