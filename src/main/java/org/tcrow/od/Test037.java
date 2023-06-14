package org.tcrow.od;

import java.util.Scanner;

/**
 * 数列还原
 * 题目描述
 * 有一个数列A[n]
 * 从A[0]开始每一项都是一个数字
 * 数列中A[n+1]都是A[n]的描述
 * 其中A[0]=1
 * 规则如下
 * A[0]:1
 * A[1]:11 含义其中A[0]=1是1个1 即11
 * 表示A[0]从左到右连续出现了1次1
 * A[2]:21 含义其中A[1]=11是2个1 即21
 * 表示A[1]从左到右连续出现了2次1
 * A[3]:1211 含义其中A[2]从左到右是由一个2和一个1组成 即1211
 * 表示A[2]从左到右连续出现了一次2又连续出现了一次1
 * A[4]:111221 含义A[3]=1211 从左到右是由一个1和一个2两个1 即111221
 * 表示A[3]从左到右连续出现了一次1又连续出现了一次2又连续出现了2次1
 * 输出第n项的结果
 * <p>
 * 输入描述
 * 数列第n项
 * 0 <= n <= 59
 * <p>
 * 输出描述
 * 数列内容
 * <p>
 * 示例一
 * 输入
 * 4
 * 输出
 * 111221
 * <p>
 * A[5] = 312211
 * A[6] = 13112221
 * A[7] = 1113213211
 */
public class Test037 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            solution(k);
        }
    }

    private static void solution(int k) {
        System.out.println(backtrack(k));
    }

    private static String backtrack(int k) {
        if (k == 0) {
            return "1";
        }
        String string = backtrack(k - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char cur = string.charAt(i);
            int j = i;
            int count = 0;
            while (j < string.length() && cur == string.charAt(j)) {
                j++;
                count++;
            }
            i = j - 1;
            sb.append(count + "" + cur);
        }
        return sb.toString();
    }
}
