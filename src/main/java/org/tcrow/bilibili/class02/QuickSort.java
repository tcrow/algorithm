package org.tcrow.bilibili.class02;

import org.tcrow.sort.Sort;
import org.tcrow.util.RandomUtil;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 荷兰国旗问题,按照num划分区域，左边比num小，右边比num大，中间等于num
     * 1、指针如果小于num，则swap(cur++,low++)
     * 2、指针等于num，cur++;
     * 3、指针大于num，swap(cur++,high--),
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int[] partition(int[] arr, int low, int high) {
        int less = low - 1;
        int more = high + 1;
        int num = arr[high];
        int cur = low;
        while (cur != more) {
            if (arr[cur] < num) {
                Sort.swap(arr, cur++, ++less);
            } else if (arr[cur] > num) {
                Sort.swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 取一个随机数跟最后一位进行交换，防止快速排序劣化，随机后平均复杂度满足NlogN
            int num = low + (int) (Math.random() * (high - low + 1));
            Sort.swap(arr, num, high);
            // 对数组进行三色分区
            int[] p = partition(arr, low, high);
            // 对左边进行快速排序
            quickSort(arr, low, p[0] - 1);
            // 对右边进行快速排序
            quickSort(arr, p[1] + 1, high);
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.getRandomArray(1, 100, 100);
        print(arr);
        for (int i = 0; i < 10; i++) {
            quickSort(arr, 0, arr.length - 1);
            print(arr);
        }
    }
}
