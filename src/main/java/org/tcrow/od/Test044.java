package org.tcrow.od;

import java.util.Scanner;
import java.util.Stack;

/**
 * 火星文计算
 * 题目描述
 * 已经火星人使用的运算符号为# $
 * 其与地球人的等价公式如下
 * x#y=2*x+3*y+4
 * x$y=3*x+y+2
 * x y是无符号整数
 * 地球人公式按照c语言规则进行计算
 * 火星人公式中$符优先级高于#相同的运算符按从左到右的顺序运算
 * <p>
 * 输入描述
 * 火星人字符串表达式结尾不带回车换行
 * 输入的字符串说明是 字符串为仅有无符号整数和操作符组成的计算表达式
 * <p>
 * 用例保证字符串中操作数与操作符之间没有任何分隔符
 * 用例保证操作数取值范围为32位无符号整数，
 * 保证输入以及计算结果不会出现整型溢出
 * 保证输入的字符串为合法的求值报文
 * 例如: 123#4$5#76$78
 * 保证不会出现非法的求值报文
 * 例如:
 * #4$5 这种缺少操作数
 * 4$5# 这种缺少操作数
 * 4#$5 这种缺少操作数
 * 4 $5 有空格
 * 3+4-5*6/7 有其他操作符
 * 12345678987654321$54321 32位整数溢出
 * 输出描述
 * 根据火星人字符串输出计算结果
 * 结尾不带回车换行
 * <p>
 * 示例一
 * 输入
 * 7#6$5#12
 * 输出
 * 226
 * 说明
 * 示例
 * <p>
 * 7#6$5#12=7#(3*6+5+2)#12
 * =7#25#12
 * =(2*7+3*25+4)#12
 * =93#12
 * =2*93+3*12+4
 * =226
 */
public class Test044 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static int process$(int x, int y) {
        return 3 * x + y + 2;
    }

    private static int processJinghao(int x, int y) {
        return 2 * x + 3 * y + 4;
    }

    private static void solution(String line) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                stack.add(line.charAt(i));
            } else if (line.charAt(i) == '$') {
                int j = i + 1;
                StringBuilder y = new StringBuilder();
                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                    y.append(line.charAt(j));
                    j++;
                }
                String x = "";
                while (!stack.isEmpty() && stack.peek() != '#') {
                    x = stack.pop() + x;
                }
                String num = ("" + process$(Integer.parseInt(x.toString()), Integer.parseInt(y.toString())));
                for (int k = 0; k < num.length(); k++) {
                    stack.add(num.charAt(k));
                }
                i = j - 1;
            } else {
                stack.add(line.charAt(i));
            }
        }

        String str = "";
        while (!stack.isEmpty()) {
            str = stack.pop() + str;
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                stack.add(str.charAt(i));
            } else if (str.charAt(i) == '#') {
                int j = i + 1;
                StringBuilder y = new StringBuilder();
                while (j < str.length() && Character.isDigit(str.charAt(j))) {
                    y.append(str.charAt(j));
                    j++;
                }
                String x = "";
                while (!stack.isEmpty()) {
                    x = stack.pop() + x;
                }
                String num = ("" + processJinghao(Integer.parseInt(x.toString()), Integer.parseInt(y.toString())));
                for (int k = 0; k < num.length(); k++) {
                    stack.add(num.charAt(k));
                }
                i = j - 1;
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        System.out.println(res);
    }
}
