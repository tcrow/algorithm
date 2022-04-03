package org.tcrow.bilibili.class02;

import org.tcrow.sort.Sort;
import org.tcrow.util.RandomUtil;

/**
 * 快速排序
 */
public class QuickSort {
    private static void quickSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) >>> 1;
        while (low <= high) {
            if (arr[low] > arr[mid]) {
                Sort.swap(arr, low, mid);
            }
            low++;

            if (arr[high] < arr[mid]) {
                Sort.swap(arr, high, mid);
            }
            high--;

//            if (arr[low] <= arr[high]) {
//                high--;
//                continue;
//            }
//            Sort.swap(arr, low, high);
//            low++;
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.getRandomArray(1, 100, 10);
        print(arr);
        quickSort(arr);
        print(arr);
    }
}
