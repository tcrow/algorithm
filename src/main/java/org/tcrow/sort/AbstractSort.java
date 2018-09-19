package org.tcrow.sort;

import java.util.Random;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public abstract class AbstractSort {

    protected static final int CUTOFF = 22;

    /**
     * 对整形数组进行排序
     *
     * @param arr
     * @return
     */
    abstract Comparable[] sort(Comparable[] arr);

    /**
     * 对数组中的从low到high的数值进行排序
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    static Comparable[] sort(Comparable[] arr, int low, int high) {
        return null;
    }

    protected static void commonMerge(Comparable[] aux, Comparable[] arr, int lo, int mid, int hi){
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                arr[k] = arr[j++];
            } else {
                arr[k] = aux[i++];
            }
        }

    }

    public static void exchange(int a, int b, Comparable[] arr) {
        Comparable tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
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
     * Fisher–Yates shuffle
     * wiki:https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     *
     * @param arr
     * @return
     */
    protected Comparable[] shuffle(Comparable[] arr) {
        int j;
        Random rd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            //generate a random integer 0<=j<=i
            j = rd.nextInt(i + 1);
            exchange(i, j, arr);
        }
        return arr;
    }
}
