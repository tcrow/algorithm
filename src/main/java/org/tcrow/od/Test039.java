package org.tcrow.od;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 第N个排列
 * 题目描述
 * 给定参数n
 * 从1到n会有n个整数 1，2，3，...n
 * 这n个数字共有n!种排列 按大小顺序升序列出所有排列情况
 * 并一一标记
 * 当n = 3 时，所有排列如下
 * "123","132","213","231","312","321"
 * 给定n和k返回第n个排列
 * <p>
 * 输入描述
 * 第一行为n
 * 第二行为k
 * n的范围是 1 ~ 9
 * k的范围是 1 ~ n!
 * <p>
 * 输出描述
 * 输出排列第k位置的数字
 * <p>
 * 示例一
 * 输入
 * 3
 * 3
 * 输出
 * 213
 * 示例二
 * 输入
 * 2
 * 2
 * 输出
 * 21
 */
public class Test039 {
    private static final TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            int n = Integer.parseInt(scanner.nextLine());
            solution(k, n);
        }
    }

    private static void solution(int k, int n) {
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = i + 1;
        }
        backtrack(0, k, arr);
        for (int i = 0; i < n - 1; i++) {
            treeSet.pollFirst();
        }
        System.out.println(treeSet.pollFirst());
    }

    private static void backtrack(int m, int n, int[] arr) {
        if (m == n) {
            add(arr);
            return;
        }

        for (int i = m; i < n; i++) {
            swap(i, m, arr);
            backtrack(m + 1, n, arr);
            swap(i, m, arr);
        }
    }

    private static void add(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i];
        }
        treeSet.add(Integer.parseInt(str));
    }

    private static void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
