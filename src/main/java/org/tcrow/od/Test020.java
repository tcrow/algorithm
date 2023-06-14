package org.tcrow.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 合并数组
 * 题目描述
 * 现在有多组整数数组，需要将他们合并成一个新的数组，
 * 合并规则：从每个数组里按顺序取出固定长度的内容，合并到新的数组。
 * 取完的内容会删除掉，如果该行不足固定长度，或者已经为空，
 * 则直接取出剩余部分的内容放到新的数组中继续下一行。
 * <p>
 * 输入描述
 * 第1行为每次读取的固定长度len，0 < len < 10，
 * 第2行是整数数组的数目num， 0 < num < 10000，
 * 第3 ~ n 行是需要合并的数组，
 * 不同的数组用换行分割，
 * 元素之间用逗号分割，
 * 最大不超过100个元素
 * <p>
 * 输出描述
 * 输出一个新的数组，用逗号分割
 * <p>
 * 示例一
 * 输入
 * 3
 * 2
 * 2,5,6,7,9,5,7
 * 1,7,4,3,4
 * 输出
 * 2,5,6,1,7,4,7,9,5,3,4,7
 * 说明
 * 获得长度3和数组数目2
 * 先遍历第一行 获得2,5,6
 * 再遍历第二行 获得1,7,4
 * 再循环回到第一行获得7,9,5
 * 再遍历第二行获得3,4
 * 再回到第一行获得7
 * <p>
 * 示例二
 * 输入
 * 4
 * 3
 * 1,2,3,4,5,6
 * 1,2,3
 * 1,2,3,4
 * 输出
 * 1,2,3,4,1,2,3,1,2,3,4,5,6
 */
public class Test020 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            int N = Integer.parseInt(scanner.nextLine());
            String[] inputs = new String[N];
            for (int i = 0; i < N; i++) {
                inputs[i] = scanner.nextLine();
            }
            solution(k, inputs);
        }
    }

    private static void solution(int k, String[] inputs) {
        int sum = 0;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            String[] split = inputs[i].split(",");
            sum += split.length;
            List<Integer> tmpList = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                tmpList.add(Integer.parseInt(split[j]));
            }
            lists.add(tmpList);
        }
        List<Integer> res = new ArrayList<>();
        int cur = 0;
        for (int i = 0; i < sum; i++) {
            int idx = cur % lists.size();
            if (lists.get(idx).isEmpty()) {
                cur++;
            }
            getNum(res, lists.get(idx), k);
            cur++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i) + ",");
        }
        System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
    }

    private static List<Integer> getNum(List<Integer> res, List<Integer> list, int k) {
        for (int i = 0; i < k; i++) {
            if (list.isEmpty()) {
                return list;
            }
            res.add(list.get(0));
            list.remove(0);
        }

        return list;
    }
}
