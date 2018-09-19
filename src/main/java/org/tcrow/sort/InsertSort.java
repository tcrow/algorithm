package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/21
 * @description
 */
public class InsertSort extends AbstractSort {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    public static Comparable[] sort(Comparable[] arr, int low, int high) {
        Comparable key;
        for (int i = low + 1; i <= high; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= low && Sort.less(key, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        return arr;
    }
}
