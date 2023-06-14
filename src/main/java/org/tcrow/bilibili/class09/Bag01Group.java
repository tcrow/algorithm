package org.tcrow.bilibili.class09;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 有依赖的01背包问题
 * 24000 40
 * 100 2 0
 * 400 5 0
 * 1300 5 1
 * 1400 3 2
 * 500 2 0
 * 800 2 0
 * 1400 5 0
 * 300 5 0
 * 1400 3 0
 * 500 2 0
 * 1800 2 0
 * 400 5 9
 * 1300 5 9
 * 1400 3 0
 * 500 2 0
 * 800 2 0
 * 1400 5 0
 * 300 5 0
 * 1400 3 0
 * 500 2 0
 * 1800 2 0
 * 400 5 20
 * 1300 5 20
 * 1400 3 0
 * 500 2 0
 * 800 2 0
 * 1400 5 0
 * 300 5 0
 * 1400 3 0
 * 500 2 0
 * 1800 2 0
 * 400 5 31
 * 1300 5 31
 * 1400 3 0
 * 500 2 0
 * 800 2 0
 * 1400 5 0
 * 300 5 0
 * 1400 3 0
 * 500 2 0
 */
public class Bag01Group {
    public static void main(String[] arg) {

        Scanner in = new Scanner(System.in);
        int totalMoney = in.nextInt() / 10;
        int totalSize = in.nextInt();

        List<Goods> list = new ArrayList<>();
        for (int i = 0; i < totalSize; i++) {
            int v = in.nextInt();
            int p = in.nextInt();
            int q = in.nextInt();
            list.add(new Goods(v / 10, p, q));
        }
        in.close();

        for (Goods goods : list) {
            if (goods.q > 0) {
                Goods parent = list.get(goods.q - 1);
                if (parent.a1 == null) {
                    parent.a1 = goods;
                    continue;
                }
                parent.a2 = goods;
            }
        }
        list = list.stream().filter(goods -> goods.q == 0).collect(Collectors.toList());

        System.out.println(dp(list, totalMoney) * 10);
        return;
    }

    private static int dp(List<Goods> list, int totalMoney) {
        int N = list.size();
        int[][] dp = new int[N + 1][totalMoney + 1];
        for (int index = N - 1; index >= 0; index--) {
            Goods goods = list.get(index);
            for (int rest = 0; rest <= totalMoney; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest + goods.weight <= totalMoney ?
                        goods.value + dp[index + 1][rest + goods.weight] : 0;
                int p3 = goods.a1 != null && rest + goods.weight + goods.a1.weight <= totalMoney ?
                        goods.value + goods.a1.value + dp[index + 1][rest + goods.weight + goods.a1.weight] : 0;
                int p4 = goods.a2 != null && rest + goods.weight + goods.a2.weight <= totalMoney ?
                        goods.value + goods.a2.value + dp[index + 1][rest + goods.weight + goods.a2.weight] : 0;
                int p5 = goods.a1 != null && goods.a2 != null && rest + goods.weight + goods.a1.weight + goods.a2.weight <= totalMoney ?
                        goods.value + goods.a1.value + goods.a2.value + dp[index + 1][rest + goods.weight + goods.a1.weight + goods.a2.weight] : 0;
                dp[index][rest] = max(p1, p2, p3, p4, p5);
                if (dp[index][rest] > 0) {
                    System.out.println(String.format("index:%s,rest:%s,dp:%s", index, rest * 10, dp[index][rest] * 10));
                }
            }
        }
        return dp[0][0];
    }

    private static int max(int... nums) {
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static class Goods {
        int weight;
        int value;
        int q;
        Goods a1;
        Goods a2;

        public Goods(int a, int b, int q) {
            this.value = a * b;
            this.weight = a;
            this.q = q;
        }
    }
}
