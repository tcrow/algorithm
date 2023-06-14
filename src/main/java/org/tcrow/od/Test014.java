package org.tcrow.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 单词接龙
 * ¶题目描述
 * 单词接龙的规则是:
 * 可用于接龙的单词,首字母必须要与前一个单词的尾字母相同；
 * 当存在多个首字母相同的单词时，取长度最长的单词；
 * 如果长度也相等，则取字典序最小的单词；
 * 已经参与接龙的单词不能重复使用；
 * 现给定一组全部由小写字母组成的单词数组，
 * 并指定其中一个单词为起始单词，进行单词接龙，
 * 请输出最长的单词串。
 * 单词串是单词拼接而成的，中间没有空格。
 * <p>
 * 单词个数 1 < N < 20
 * 单个单词的长度 1 ~ 30
 * <p>
 * 输入描述
 * 输入第一行为一个非负整数
 * 表示起始单词在数组中的索引k
 * 0 <= k < N
 * 输入的第二行为非负整数N
 * 接下来的N行分别表示单词数组中的单词
 * <p>
 * 输出描述
 * 输出一个字符串表示最终拼接的单词串
 * <p>
 * 示例一
 * 输入
 * 0
 * 6
 * word
 * dd
 * da
 * dc
 * dword
 * d
 * 输出
 * worddwordda
 * 示例一
 * 输入
 * 4
 * 6
 * word
 * dd
 * da
 * dc
 * dword
 * d
 * 输出
 * dwordda
 */
public class Test014 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            int N = Integer.parseInt(scanner.nextLine());
            String[] array = new String[N];
            for (int i = 0; i < N; i++) {
                array[i] = scanner.nextLine();
            }
            solution(k, array);
        }
    }

    private static void solution(int k, String[] array) {
        StringBuilder res = new StringBuilder(array[k]);
        List<String> list = new ArrayList<>(Arrays.asList(array));
        list.remove(k);
        res.append(process(res, list));
        System.out.println(res);
    }

    private static String process(StringBuilder res, List<String> list) {
        char c = res.charAt(res.length() - 1);
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(0) == c) {
                tmp.add(list.get(i));
            }
        }
        if (tmp.isEmpty()) {
            return "";
        }
        tmp.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o2.length() - o1.length();
        });
        list.remove(tmp.get(0));
        res.append(tmp.get(0));
        return process(res, list);
    }

}
