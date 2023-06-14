package org.tcrow.leetcode;

import java.util.Arrays;

public class Test016 {
    public static void main(String[] args) {
        int num = threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
//        int num = threeSumClosest(new int[]{0, 0, 0}, 1);
        System.out.println(num);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return best;
    }
}
