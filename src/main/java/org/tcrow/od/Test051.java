package org.tcrow.od;

import java.util.Scanner;

/**
 * 停车场最大距离
 * 题目描述
 * 停车场有一横排车位0代表没有停车,1代表有车.
 * 至少停了一辆车在车位上,也至少有一个空位没有停车.
 * 为防止刮蹭,需为停车人找到一个车位
 * 使得停车人的车最近的车辆的距离是最大的
 * 返回此时的最大距离
 * <p>
 * 输入描述
 * 一个用半角逗号分割的停车标识字符串,停车标识为0或1,
 * 0为空位,1为已停车
 * 停车位最多有100个
 * 输出描述
 * 输出一个整数记录最大距离
 * 示例一
 * 输入
 * 1,0,0,0,0,1,0,0,1,0,1
 * 输出
 * 2
 * 说明
 * 当车停在第三个位置上时,离其最近的车距离为2(1~3)
 * 当车停在第四个位置上时,离其最近的车距离为2(4~6)
 * 其他位置距离为1
 * 因此最大距离为2
 */
public class Test051 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        String[] split = line.split(",");
        int[] arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }

        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
                max = Math.max(max, getLen(arr, i));
                arr[i] = 0;
            }
        }

        System.out.println(max);
    }

    private static int getLen(int[] arr, int index) {
        int leftLen = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                leftLen = index - i;
                break;
            }
        }

        int rightLen = 0;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                rightLen = i - index;
                break;
            }
        }
        return Math.min(leftLen, rightLen);
    }
}
