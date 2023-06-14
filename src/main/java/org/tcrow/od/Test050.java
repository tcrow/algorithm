package org.tcrow.od;

import java.util.Scanner;

/**
 * 非严格递增连续数字序列
 * 题目描述
 * 输入一个字符串仅包含大小写字母和数字
 * 求字符串中包含的最长的非严格递增连续数字序列长度
 * 比如：
 * 12234属于非严格递增数字序列
 * <p>
 * 输入描述
 * 输入一个字符串仅包含大小写字母和数字
 * <p>
 * 输出描述
 * 输出字符串中包含的最长的非严格递增连续数字序列长度
 * <p>
 * 示例一
 * 输入
 * abc2234019A334bc
 * 输出
 * 4
 * 说明
 * 2234为最长的非严格递增连续数字序列，所以长度为4
 */
public class Test050 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        char[] chars = line.toCharArray();
        int max = 0;
        int curLen = 0;
        char last = 'a';
        for (char cur : chars) {
            if (Character.isDigit(cur)) {
                if (curLen == 0 || cur >= last) {
                    curLen++;
                } else {
                    max = Math.max(max, curLen);
                    curLen = 1;
                }
                last = cur;
            } else {
                max = Math.max(max, curLen);
                curLen = 0;
                last = 'a';
            }
        }
        max = Math.max(max,curLen);
        System.out.println(max);
    }
}
