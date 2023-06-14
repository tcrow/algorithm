package org.tcrow.od;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 找出同班小朋友
 * 题目描述
 * 幼儿园两个班的小朋友排队时混在了一起
 * 每个小朋友都知道自己跟前面一个小朋友是不是同班
 * 请你帮忙把同班的小朋友找出来
 * 小朋友的编号为整数
 * 与前面一个小朋友同班用Y表示
 * 不同班用N表示
 * <p>
 * 输入描述
 * 输入为空格分开的小朋友编号和是否同班标志
 * 比如 6/N 2/Y 3/N 4/Y
 * 表示一共有4位小朋友
 * 2和6是同班，3和2不同班，4和3同班
 * 小朋友总数不超过999
 * 0 < 每个小朋友编号 < 999
 * 不考虑输入格式错误
 * <p>
 * 输出描述
 * 每一行记录一班小朋友的编号 编号用空格分开
 * 并且
 * <p>
 * 编号需要按照大小升序排列，分班记录中第一个编号小的排在第一行
 * 如果只有一个班的小朋友 第二行为空
 * 如果输入不符合要求输出字符串ERROR
 * 示例一
 * 输入
 * 1/N 2/Y 3/N 4/Y
 * 输出
 * 1 2
 * 3 4
 * 说明
 * 2的同班标记为Y因此和1同班
 * 3的同班标记位N因此和1,2不同班
 * 4的同班标记位Y因此和3同班
 */
public class Test038 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            solution(line);
        }
    }

    private static void solution(String line) {
        String[] split = line.split(" ");
        if (split.length > 999) {
            System.out.println("ERROR");
            return;
        }
        for (String str : split) {
            String[] arr = str.split("/");
            if (arr.length != 2) {
                System.out.println("ERROR");
                return;
            }
            for (int i = 0; i < arr[0].length(); i++) {
                if (!Character.isDigit(arr[0].charAt(i))) {
                    System.out.println("ERROR");
                    return;
                }
            }
            if (Integer.parseInt(arr[0]) > 999) {
                System.out.println("ERROR");
                return;
            }
            if (arr[1].length() != 1) {
                System.out.println("ERROR");
                return;
            }

            if (arr[1].charAt(0) != 'Y' && arr[1].charAt(0) != 'N') {
                System.out.println("ERROR");
                return;
            }
        }
        TreeSet<Integer> linea = new TreeSet<>();
        TreeSet<Integer> lineb = new TreeSet<>();
        // false 入a true 入b
        boolean flag = false;
        for (int i = split.length - 1; i > -1; i--) {
            String str = split[i];
            String[] arr = str.split("/");
            if (flag) {
                lineb.add(Integer.parseInt(arr[0]));
            } else {
                linea.add(Integer.parseInt(arr[0]));
            }

            if (arr[1].equals("N")) {
                flag = !flag;
            }
        }

        if (linea.isEmpty() || lineb.isEmpty()) {
            if(linea.isEmpty()){
                for (Integer integer : lineb) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }else {
                for (Integer integer : linea) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
            return;
        }

        TreeSet<Integer> treea;
        TreeSet<Integer> treeb;
        if (linea.first() < lineb.first()) {
            treea = linea;
            treeb = lineb;
        } else {
            treea = lineb;
            treeb = linea;
        }

        for (Integer integer : treea) {
            System.out.print(integer + " ");
        }
        System.out.println();

        for (Integer integer : treeb) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

}
