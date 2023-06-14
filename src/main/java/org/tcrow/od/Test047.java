package org.tcrow.od;

import java.util.Scanner;

/**
 * 求字符串中所有整数的最小和
 * 题目描述
 * 说明
 * <p>
 * 字符串s，只包含 a-z A-Z + - ；
 * 合法的整数包括
 * 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
 * 2）负整数 负号 - 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
 * 输入描述
 * 包含数字的字符串
 * <p>
 * 输出描述
 * 所有整数的最小和
 * <p>
 * 示例一
 * 输入
 * bb1234aa
 * 输出
 * 10
 * 示例二
 * 输入
 * bb12-34aa
 * 输出
 * -31
 * 说明
 * 1+2+(-34) = 31
 */
public class Test047 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        char[] chars = line.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                continue;
            }
            if (Character.isDigit(chars[i])) {
                sum += Integer.parseInt(String.valueOf(chars[i]));
                continue;
            }

            if (chars[i] == '-') {
                StringBuilder sb = new StringBuilder("-");
                for (int j = i + 1; j < chars.length; j++) {
                    if (Character.isDigit(chars[j])) {
                        sb.append(chars[j]);
                    } else {
                        i = j - 1;
                        break;
                    }
                }
                if (sb.length() > 1) {
                    sum += Integer.parseInt(sb.toString());
                }
            }
        }
        System.out.println(sum);
    }
}
