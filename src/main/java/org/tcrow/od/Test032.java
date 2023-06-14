package org.tcrow.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 众数和中位数
 * 题目描述
 * 众数是指一组数据中出现次数多的数
 * 众数可以是多个
 * 中位数是指把一组数据从小到大排列，最中间的那个数，
 * 如果这组数据的个数是奇数，那最中间那个就是中位数
 * 如果这组数据的个数为偶数，那就把中间的两个数之和除以2就是中位数
 * 查找整型数组中元素的众数并组成一个新的数组
 * 求新数组的中位数
 * 输入描述
 * 输入一个一维整型数组，数组大小取值范围 0 < n < 1000
 * 数组中每个元素取值范围， 0 < e < 1000
 * <p>
 * 输出描述
 * 输出众数组成的新数组的中位数
 * <p>
 * 示例一
 * 输入
 * 10 11 21 19 21 17 21 16 21 18 16
 * 输出
 * 21
 * 示例二
 * 输入
 * 2 1 5 4 3 3 9 2 7 4 6 2 15 4 2 4
 * ¶输出
 * 3
 * 示例三
 * 输入
 * 5 1 5 3 5 2 5 5 7 6 7 3 7 11 7 55 7 9 98 9 17 9 15 9 9 1 39
 * 输出
 * 7
 */
public class Test032 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            solution(input);
        }
    }

    private static void solution(String input) {
        List<Integer> collect = Arrays.stream(input.split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : collect) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }

        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            max = Math.max(value, max);
        }

        List<Integer> newList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == max) {
                newList.add(entry.getKey());
            }
        }

        newList.sort(Integer::compareTo);

        if (newList.size() % 2 == 1) {
            System.out.println(newList.get(newList.size() / 2));
        } else {
            System.out.println((newList.get(newList.size() / 2) + newList.get(newList.size() / 2 - 1)) / 2);
        }
    }
}
