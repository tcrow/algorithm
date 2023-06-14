package org.tcrow.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目名称
 * 题目描述
 * 给定一个非空数组(列表)，起元素数据类型为整型，
 * 请按照数组元素十进制最低位从小到大进行排序，
 * 十进制最低位相同的元素，相对位置保持不变，
 * 当数组元素为负值时，十进制最低为等同于去除符号位后对应十进制值最低位。
 * <p>
 * 输入描述
 * 给定一个非空数组(列表)
 * 其元素数据类型为32位有符号整数
 * 数组长度为[1,1000]
 * 输出排序后的数组
 * <p>
 * 输出描述
 * 输出排序后的数组
 * <p>
 * 示例一
 * 输入
 * 1,2,5,-21,22,11,55,-101,42,8,7,32
 * 输出
 * 1,-21,11,-101,2,22,42,32,5,55,7,8
 */
public class Test025 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            solution(input);
        }
    }

    private static void solution(String input) {
        String[] split = input.split(",");
        Arrays.sort(split, (o1, o2) -> {
            int a = Integer.parseInt(o1.substring(o1.length() - 1));
            int b = Integer.parseInt(o2.substring(o2.length() - 1));
            return a - b;
        });
        StringBuilder sb = new StringBuilder();
        for (String str : split) {
            sb.append(str + ",");
        }
        System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
    }
}
