package org.tcrow.bilibili.class03;

import org.tcrow.util.PrintUtil;
import org.tcrow.util.RandomUtil;

import java.util.Arrays;

/**
 * 桶排序
 * https://www.bilibili.com/video/BV13g41157hK?p=4
 */
public class BucketSort {
    /**
     * 获取数组最大多少位
     *
     * @param arr
     * @return
     */
    private static int getDigit(int[] arr) {
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    /**
     * 获取当前位数字是多少
     *
     * @param num
     * @param digit
     * @return
     */
    private static int getDigit(final int num, final int digit) {
        return num / (int) Math.pow(10, digit - 1) % 10;
    }

    /**
     * 桶排序步骤
     *
     * @param arr
     */
    private static void bucketSort(int[] arr) {
        int[] bucket = new int[arr.length];
        int digit = getDigit(arr);
        final int radix = 10;
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            // count表示个位等于x有多少个
            for (int num : arr) {
                int x = getDigit(num, d);
                count[x]++;
            }

            // 把count修正为各位小于等于x的有多少个
            for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 从右往左依次入桶
            for (int i = arr.length - 1; i >= 0; i--) {
                int x = getDigit(arr[i], d);
                bucket[count[x] - 1] = arr[i];
                count[x]--;
            }

            // 出桶
            for (int i = 0; i < bucket.length; i++) {
                arr[i] = bucket[i];
            }
        }

    }

    public static void main(String[] args) {
        int[] array = RandomUtil.getRandomArray(1, 100, 10);
        PrintUtil.print(array);
        bucketSort(array);
        PrintUtil.print(array);
        Arrays.sort(array);
        PrintUtil.print(array);
    }
}
