package org.tcrow.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目名称
 * 题目描述
 * 某学校举行运动会,学生们按编号(1、2、3.....n)进行标识,
 * 现需要按照身高由低到高排列，
 * 对身高相同的人，按体重由轻到重排列，
 * 对于身高体重都相同的人，维持原有的编号顺序关系。
 * 请输出排列后的学生编号
 * <p>
 * 输入描述
 * 两个序列，每个序列由N个正整数组成，(0 < n <= 100)。
 * 第一个序列中的数值代表身高，第二个序列中的数值代表体重。
 * <p>
 * 输出描述
 * 排列结果，每个数据都是原始序列中的学生编号，编号从1开始。
 * <p>
 * 示例一
 * 输入
 * 4
 * 100 100 120 130
 * 40 30 60 50
 * 输出
 * 2 1 3 4
 * 示例一
 * 输入
 * 3
 * 90 110 90
 * 45 60 45
 * 输出
 * 1 3 2
 */
public class Test022 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String heights = scanner.nextLine();
            String weights = scanner.nextLine();
            solution(heights, weights);
        }
    }

    private static void solution(String heights, String weights) {
        String[] heightsSplit = heights.split(" ");
        String[] weightsSplit = weights.split(" ");
        int k = heightsSplit.length;
        String[] array = new String[k];
        for (int i = 0; i < k; i++) {
            array[i] = heightsSplit[i] + " " + weightsSplit[i] + " " + (i + 1);
        }

        Arrays.sort(array, (o1, o2) -> {
            String[] arr1 = o1.split(" ");
            String[] arr2 = o2.split(" ");
            int height1 = Integer.parseInt(arr1[0]);
            int height2 = Integer.parseInt(arr2[0]);
            int weight1 = Integer.parseInt(arr1[1]);
            int weight2 = Integer.parseInt(arr2[1]);
            if (height1 != height2) {
                return height1 - height2;
            }

            if (weight1 != weight2) {
                return weight1 - weight2;
            }
            return 0;
        });

        for (int i = 0; i < k; i++) {
            System.out.print(array[i].split(" ")[2] + " ");
        }
    }
}
