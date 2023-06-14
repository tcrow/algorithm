package org.tcrow.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 最小叶子节点
 * 题目描述
 * 二叉树也可以用数组来存储，
 * 给定一个数组，树的根节点的值储存在下标1，
 * 对于储存在下标n的节点，他的左子节点和右子节点分别储存在下标2*n和2*n+1，
 * 并且我们用-1代表一个节点为空，
 * 给定一个数组存储的二叉树，
 * 试求从根节点到最小的叶子节点的路径，
 * 路径由节点的值组成。
 * <p>
 * 输入描述
 * 输入一行为数组的内容，
 * 数组的每个元素都是正整数，元素间用空格分割，
 * 注意第一个元素即为根节点的值，
 * 即数组的第n元素对应下标n，
 * 下标0在树的表示中没有使用，所以我们省略了，
 * 输入的树最多为7层。
 * <p>
 * 输出描述
 * 输出从根节点到最小叶子节点的路径上各个节点的值，
 * 由空格分割，
 * 用例保证最小叶子节点只有一个。
 * <p>
 * 示例一
 * 输入
 * 3 5 7 -1 -1 2 4
 * 输出
 * 3 7 2
 * 示例二
 * 输入
 * 5 9 8 -1 -1 7 -1 -1 -1 -1 -1 6
 * 输出
 * 5 8 7 6
 */
public class Test011 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            List<Integer> list = Arrays.asList(split).stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList());
            int[] array = new int[list.size() + 1];
            for (int i = 1; i <= list.size(); i++) {
                array[i] = list.get(i - 1);
            }
            solution(array);
        }
    }

    /**
     * 先找到所有叶子节点
     * 然后找最小叶子节点
     * 根据最小叶子节点入栈直到根节点
     * 栈内所有数据出栈打印
     *
     * @param array
     */
    private static void solution(int[] array) {
        // 找叶子节点
        List<Integer> child = new ArrayList<>();
        for (int i = 1; i < array.length; i++) {
            if (array[i] == -1) {
                continue;
            }

            if (i * 2 >= array.length && i * 2 + 1 >= array.length) {
                child.add(i);
                continue;
            }

            if (i * 2 < array.length && i * 2 + 1 < array.length) {
                if (array[i * 2] == -1 && array[i * 2 + 1] == -1) {
                    child.add(i);
                    continue;
                }
            }

            if (i * 2 >= array.length && i * 2 + 1 < array.length) {
                if (array[i * 2 + 1] == -1) {
                    child.add(i);
                    continue;
                }
            }

            if (i * 2 + 1 >= array.length && i * 2 < array.length) {
                if (array[i * 2] == -1) {
                    child.add(i);
                }
            }
        }

        // 找最小叶子节点
        int min = Integer.MAX_VALUE;
        int minSub = Integer.MAX_VALUE;
        for (Integer integer : child) {
            if (array[integer] < min) {
                min = array[integer];
                minSub = integer;
            }
        }

        // 入栈到根节点
        Stack<Integer> stack = new Stack<>();
        int curr = minSub;
        while (curr > 0) {
            stack.push(curr);
            if (curr % 2 == 0) {
                curr = curr / 2;
            } else {
                curr = (curr - 1) / 2;
            }
        }

        // 打印出栈
        while (!stack.isEmpty()) {
            System.out.printf(array[stack.pop()] + " ");
        }
    }
}
