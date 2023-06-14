package org.tcrow.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 磁盘容量
 * 题目描述
 * 磁盘的容量单位常用的有M、G、T
 * 他们之间的换算关系为1T =1024G，1G=1024M
 * 现在给定n块磁盘的容量，请对他们按从小到大的顺序进行稳定排序
 * 例如给定5块盘的容量
 * 5
 * 1T
 * 20M
 * 3G
 * 10G6T
 * 3M12G9M
 * 排序后的结果为
 * 20M
 * 3G
 * 3M12G9M
 * 1T
 * 10G6T
 * 注意单位可以重复出现
 * 上述3M12G9M表示的容量即为3M12G9M和12M12G相等
 * <p>
 * 输入描述
 * 输入第一行包含一个整数n，2 <= n<= 100，表示磁盘的个数。
 * 接下来的n行，每行一个字符串，2 < 长度 < 30，表示磁盘的容量，
 * 由一个或多个格式为MV的子串组成，其中M表示容量大小，V表示容量单位，
 * 例如20M、1T。
 * 磁盘容量的范围1 ~ 1024的正整数，单位M、G、T。
 * <p>
 * 输出描述
 * 输出n行
 * 表示n块磁盘容量排序后的结果
 * <p>
 * 示例一
 * 输入
 * 3
 * 1G
 * 2G
 * 1024M
 * 输出
 * 1G
 * 1024M
 * 2G
 * 说明
 * 稳定排序要求相等值保留原来位置
 * <p>
 * 示例二
 * 输入
 * 3
 * 2G4M
 * 3M2G
 * 1T
 * 输出
 * 3M2G
 * 2G4M
 * 1T
 */
public class Test021 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String[] inputs = new String[k];
            for (int i = 0; i < k; i++) {
                inputs[i] = scanner.nextLine();
            }
            solution(inputs);
        }
    }

    private static void solution(String[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < inputs[i].length(); j++) {
                if (inputs[i].charAt(j) >= '0' && inputs[i].charAt(j) <= '9') {
                    sb.append(inputs[i].charAt(j));
                } else if (inputs[i].charAt(j) == 'M') {
                    sum += Integer.parseInt(sb.toString());
                } else if (inputs[i].charAt(j) == 'G') {
                    sum += Integer.parseInt(sb.toString()) * 1024;
                } else {
                    sum += Integer.parseInt(sb.toString()) * 1024 * 1024;
                }
            }
            inputs[i] += " " + sum;
        }

        Arrays.sort(inputs, (o1, o2) -> {
            String[] arr1 = o1.split(" ");
            String[] arr2 = o2.split(" ");

            return Integer.parseInt(arr1[1]) - Integer.parseInt(arr2[1]);
        });

        for (int i = 0; i < inputs.length; i++) {
            String[] arr = inputs[i].split(" ");
            System.out.println(arr[0]);
        }
    }
}
