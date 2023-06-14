package org.tcrow.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class Test406 {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length <= 1) {
            return people;
        }

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        LinkedList<Integer[]> queue = new LinkedList<>();

        for (int[] person : people) {
            queue.add(person[1], convert(person));
        }

        int[][] res = new int[queue.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = convert(queue.poll());
        }
        return res;
    }

    private Integer[] convert(int[] people) {
        return new Integer[]{people[0], people[1]};
    }

    private int[] convert(Integer[] people) {
        return new int[]{people[0], people[1]};
    }

    public static void main(String[] args) {
        Test406 test406 = new Test406();
        int[][] nums = new int[][]{{2, 0}, {1, 1}};
        test406.print(nums);
        int[][] ints = test406.reconstructQueue(nums);
        test406.print(ints);
    }

    private void print(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print("[" + ints[i][0] + "," + ints[i][1] + "],");
        }
        System.out.println();
    }
}
