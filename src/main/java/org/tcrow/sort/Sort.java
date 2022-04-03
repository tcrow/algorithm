package org.tcrow.sort;

import com.google.common.base.Strings;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public class Sort {

    /**
     * 数组中两个值交换
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        if (arr[a] == arr[b]) {
            return;
        }
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }


    /**
     * 数组中两个值交换
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int a, int b, Comparable[] arr) {
        Comparable tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    public static Comparable[] sort(Comparable[] arr, String sortType, String type) {

        if (Strings.isNullOrEmpty(sortType)) {
            sortType = "asc";
        }

        Comparable[] result = arr;

        if ("bubble".equals(type)) {
            result = new BubbleSort().sort(arr);
        } else if ("simple".equals(type)) {
            result = new SimpleChoiceSort().sort(arr);
        } else if ("insert".equals(type)) {
            result = new InsertSort().sort(arr);
        } else if ("shell".equals(type)) {
            result = new ShellSort().sort(arr);
        } else if ("quick".equals(type)) {
            result = new QuickSort().sort(arr);
        }

        if (!"asc".equals(sortType.toLowerCase())) {
            return Sort.reverse(arr);
        }
        return result;
    }

    /**
     * 倒序数组
     *
     * @param result
     * @return
     */
    public static Comparable[] reverse(Comparable[] result) {
        Comparable[] tmp = new Comparable[result.length];
        for (int i = 0; i < result.length; i++) {
            tmp[result.length - i - 1] = result[i];
        }
        return tmp;
    }

    /**
     * 比较a,b大小，如果a < b 则返回 true 否则返回false
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 检查数组是否按照从小到大排序
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int amt = 0;
        for (int i = 0; i < 15; i++) {
            amt += 5 * Math.pow(1.05, i);
        }
        System.out.println(amt);
    }
}
