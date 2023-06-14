package org.tcrow.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 能力组队
 * 题目描述
 * 用数组代表每个人的能力，
 * 一个比赛活动要求，参赛团队的最低能力值为N
 * 每个团队可以由一人或者两人组成，
 * 且一个人只能参加一个团队，
 * 计算出最多可以派出多少只符合要求的队伍。
 * <p>
 * 输入描述
 * 第一行代表总人数，范围1 ~ 500000
 * 第二行数组代表每个人的能力
 * 数组大小范围1 ~ 500000
 * 元素取值范围1 ~ 500000
 * 第三行数值为团队要求的最低能力值1 ~ 500000
 * <p>
 * 输出描述
 * 最多可以派出的团队数量
 * <p>
 * 示例一
 * 输入
 * 5
 * 3 1 5 7 9
 * 8
 * 输出
 * 3
 * 说明
 * 3、5组成一队
 * 1、7一队
 * 9自己一队
 * 输出3
 * <p>
 * 示例二
 * 输入
 * 7
 * 3 1 5 7 9 2 6
 * 8
 * 输出
 * 4
 */
public class Test034 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            String input = scanner.nextLine();
            int N = Integer.parseInt(scanner.nextLine());
            solution(input, N);
        }
    }

    private static void solution(String input, int N) {
        List<Integer> list = Arrays.stream(input.split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        Set<Integer> set = new HashSet<>();

        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (set.contains(list.get(i))) {
                continue;
            }
            if (list.get(i) >= N) {
                set.add(list.get(i));
                res++;
                continue;
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (set.contains(list.get(j))) {
                    continue;
                }
                if (list.get(i) + list.get(j) >= N) {
                    set.add(list.get(i));
                    set.add(list.get(j));
                    res++;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
