package org.tcrow.leetcode;

public class Test027 {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int num = removeElement(nums, 1);
//        int num = removeElement(new int[]{0, 0, 0}, 1);
        print(nums, num);

    }

    public static void print(int[] nums, int p) {
        System.out.println(p);
        for (int i = 0; i < p; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int p = nums.length - 1;
        int count = 0;
        for (int i = 0; i < nums.length - count; i++) {
            if (nums[i] == val) {
                while (nums[p] == val && p > i) {
                    count++;
                    p--;
                }
                swap(nums, i, p--);
                count++;
            }
        }

        return nums.length - count;
    }

    public static void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
