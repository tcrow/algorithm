package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public abstract class AbstractSort {

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
            } else if (Sort.less(aux[j], aux[i])) {
                arr[k] = arr[j++];
            } else {
                arr[k] = aux[i++];
            }
        }

    }

}
