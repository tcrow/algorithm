package org.tcrow.od;

import java.util.Scanner;

/**
 * 相对开音节
 * 题目描述
 * 相对开音节构成的结构为辅音+元音(aeiou)+辅音(r除外) + e
 * 常见的单词有bike cake
 * 给定一个字符串，以空格为分隔符
 * 反转每个单词的字母
 * 若单词中包含如数字等其他非字母时不进行反转
 * 反转后计算其中含有相对开音节结构的子串个数
 * (连续子串中部分字符可以重复)
 * <p>
 * 输入描述
 * 字符串 以空格分割的多个单词
 * 长度<10000 字母只考虑小写
 * <p>
 * 输出描述
 * 含有相对开音节结构的子串个数
 * <p>
 * 示例一
 * 输入
 * ekam a ekac
 * 输出
 * 2
 * 说明
 * 反转后为make a cake其中make和cake为相对开音节子串
 * 返回2
 * <p>
 * 示例二
 * 输入
 * !ekam a ekekac
 * 输出
 * 2
 * 说明
 * 反转后为 !ekam a cakeke
 * 因为!ekam含有非英文字母，所以未反转
 * 其中 cake和keke 为相对开音节子串 返回2
 */
public class Test045 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (!split[i].matches("^[a-z]*$")) {
                continue;
            }
            split[i] = reserve(split[i]);
        }
        int res = 0;
        for (String str : split) {
            res += check(str);
        }
        System.out.println(res);
    }

    private static int check(String str) {
        if (!str.matches("^[a-z]*$")) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < str.length() - 3; i++) {
            if (!"aeiou".contains(String.valueOf(str.charAt(i)))
                    && "aeiou".contains(String.valueOf(str.charAt(i + 1)))
                    && !"aeiour".contains(String.valueOf(str.charAt(i + 2)))
                    && "e".contains(String.valueOf(str.charAt(i + 3)))
            ) {
                sum++;
            }
        }
        return sum;
    }

    private static String reserve(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            swap(chars, i, chars.length - 1 - i);
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }

    private static void swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }
}
