package org.tcrow.bilibili.class09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 有依赖的01背包问题
 */
public class Bag01Group {
    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        int totalMoney = in.nextInt();
        int totalSize = in.nextInt();
        int[] values = new int[totalSize];
        int[] weights = new int[totalSize];
        int[] enos = new int[totalSize];
        List<Goods> list = new ArrayList<>();
        for (int i = 0; i < totalSize; i++) {
            int v = in.nextInt();
            int p = in.nextInt();
            int q = in.nextInt();
            values[i] = v;
            weights[i] = p;
            enos[i] = q;
            list.add(new Goods(v, p, q));
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

//        System.out.println(process1(values, weights, enos, 0, 0, totalMoney));
//        System.out.println(process2(values, weights, enos, 0, 0, 0, totalMoney));
        System.out.println(process(list, 0, 0, 0, totalMoney));
        return;
    }

    private static int process(List<Goods> list, int i, int alreadyWeight, int alreadyValue, int totalMoney) {
        if (alreadyWeight > totalMoney) {
            return 0;
        }
        if (i == list.size()) {
            return alreadyValue;
        }

        Goods goods = list.get(i);
        // 五种情况，不要商品，要商品不要附件，要商品和附件一，要商品和附件二，要商品和附件1+2
        // 不要商品
        int max1 = process(list, i + 1, alreadyWeight, alreadyValue, totalMoney);
        // 要商品不要附件
        int max2 = process(list, i + 1, alreadyWeight + goods.weight, alreadyValue + goods.value, totalMoney);
        // 要商品和附件一
        int max3 = goods.a1 != null ? process(list, i + 1, alreadyWeight + goods.weight + goods.a1.weight, alreadyValue + goods.value + goods.a1.value, totalMoney) : 0;
        // 要商品和附件二
        int max4 = goods.a2 != null ? process(list, i + 1, alreadyWeight + goods.weight + goods.a2.weight, alreadyValue + goods.value + goods.a2.value, totalMoney) : 0;
        // 要商品和附件1+2
        int max5 = goods.a1 != null && goods.a2 != null ? process(list, i + 1, alreadyWeight + goods.weight + goods.a2.weight + goods.a2.weight, alreadyValue + goods.value + goods.a1.value + goods.a2.value, totalMoney) : 0;

        return Math.max(Math.max(Math.max(Math.max(max1, max2), max3), max4), max5);
    }

    /**
     * 经测试这个算法是错的
     *
     * @param values
     * @param weights
     * @param enos
     * @param i
     * @param alreadyWeight
     * @param totalMoney
     * @return
     */
    private static int process1(int[] values, int[] weights, int[] enos, int i, int alreadyWeight, int totalMoney) {
        if (alreadyWeight > totalMoney) {
            return 0;
        }

        if (i == weights.length) {
            return 0;
        }

        return Math.max(process1(values, weights, enos, i + 1, alreadyWeight, totalMoney),
                (values[i] + process1(values, weights, enos, i + 1, alreadyWeight + weights[i], totalMoney)));
    }

    private static int process2(int[] values, int[] weights, int[] enos, int i, int alreadyWeight, int alreadyValue, int totalMoney) {
        if (alreadyWeight > totalMoney) {
            return 0;
        }

        if (i == values.length) {
            return alreadyValue;
        }

        if (enos[i] == 0) {
            return Math.max(process2(values, weights, enos, i + 1, alreadyWeight, alreadyValue, totalMoney),
                    process2(values, weights, enos, i + 1, alreadyWeight + weights[i], alreadyValue + values[i], totalMoney));
        }

        return process2(values, weights, enos, i + 1, alreadyWeight, alreadyValue, totalMoney);
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
