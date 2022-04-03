package org.tcrow.bilibili.class02;

import org.tcrow.util.PrintUtil;
import org.tcrow.util.RandomUtil;

/**
 * 归并排序 可以用来求小和
 */
public class MergeSort {

    /**
     * 归并排序merge过程说明：
     * 1、创建一个临时空间，长度 = 右边 - 左边 + 1，就是需要排序的位置长度
     * 2、创建两个下标，一个指向左边第一位，一个指向右边第一位
     * 3、左边下标值和右边下标值比较，谁小谁放在临时空间第一位，一样大，把右边放第一位（之所以要用右边是在求小和时，只能用右边）
     * 4、循环3步骤，直到所有数据全部存放到新的空间内，然后将新空间排序完成的数组，覆盖掉原来数据相同位置，完成排序
     *
     * @param arr  数组
     * @param low  左边界
     * @param mid  中间
     * @param high 右边界
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        // 归并排序要创建一个临时空间
        int[] temp = new int[high - low + 1];
        int i = 0;
        int p0 = low;
        int p1 = mid + 1;

        // 左边下标值和右边下标值比较，谁小谁放在临时空间第一位，一样大，把右边放第一位（之所以要用右边是在求小和时，只能用右边）
        while (p0 <= mid && p1 <= high) {
            temp[i++] = arr[p0] < arr[p1] ? arr[p0++] : arr[p1++];
        }

        // 复制左边剩余数据到临时空间
        while (p0 <= mid) {
            temp[i++] = arr[p0++];
        }

        // 复制右边剩余数据到临时空间
        while (p1 <= high) {
            temp[i++] = arr[p1++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[low + j] = temp[j];
        }
    }

    private static void process(int[] arr, int low, int high) {
        if (low == high) {
            return;
        }
        // 求中间数
        int mid = low + ((high - low) >> 1);
        process(arr, low, mid);
        process(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private static void mergeSort(int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.getRandomArray(1, 100, 100);
        PrintUtil.print(arr);
        for (int i = 0; i < 10; i++) {
            mergeSort(arr);
            PrintUtil.print(arr);
        }
    }
}
