package org.tcrow.od;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * N进制减法
 * 题目描述
 * 实现一个基于字符串的N进制的减法
 * 需要对输入的两个字符串按照给定的N进制进行减法操作
 * 输出正负符号和表示结果的字符串
 * <p>
 * 输入描述
 * 输入三个参数
 * 第一个参数是整数形式的进制N值
 * N值范围大小为 2 <= N <= 35
 * 第二个参数为被减数字符串
 * 第三个参数为减数字符串
 * 有效的字符包括0~9以及小写字母a~z
 * 字符串有效字符个数最大为100个字符
 * 另外还有结尾的\0
 * 限制：输入的被减数和减数除了单独的0以外 不能是以0开头的字符串
 * 如果输入有异常或计算过程中有异常 此时应当输出-1表示错误
 * <p>
 * 输出描述
 * 结果是两个
 * 第一个是减法计算的结果
 * -1表示出错 0表示结果为整数 1表示结果为负数
 * 其二为表示结果的字符串
 * <p>
 * 示例一
 * 输入
 * 2 11 1
 * 输出
 * 0 10
 * 说明
 * 按二进制11-1计算正常
 * 0表示符号为正数，结果为10
 * <p>
 * 示例二
 * 输入
 * 8 07 1
 * 输出
 * -1
 * 说明
 * 按8进制检查的减数不符合非零前导的要求，返回结果为-1
 * 没有其他结果内容
 */
public class Test049 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        List<String> list = Arrays.stream(line.split(" "))
                .collect(Collectors.toList());
        if (!list.get(0).matches("^[1-9][0-9]*$")) {
            System.out.println(-1);
            return;
        }

        if (!list.get(1).matches("^([0-9]|[a-z])*$") || list.get(1).startsWith("0") || list.get(2).length() > 100) {
            System.out.println(-1);
            return;
        }

        if (!list.get(2).matches("^([0-9]|[a-z])*$") || list.get(2).startsWith("0") || list.get(2).length() > 100) {
            System.out.println(-1);
            return;
        }

        int n = Integer.parseInt(list.get(0));

        if (n < 2 || n > 35) {
            System.out.println(-1);
            return;
        }

        BigInteger a = new BigInteger(list.get(1), n);
        BigInteger b = new BigInteger(list.get(2), n);
        BigInteger res = a.subtract(b);
        int signum = res.signum();
        String resStr = res.abs().toString(n);
        if (signum == -1) {
            System.out.println(1 + " " + resStr);
        } else {
            System.out.println(0 + " " + resStr);
        }
    }
}
