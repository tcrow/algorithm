package org.tcrow.sort;

import com.google.common.base.Strings;
import org.tcrow.datastructure.MinPq;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public class Sort {

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
        } else if ("quick3way".equals(type)) {
            result = new Quick3Way().sort(arr);
        } else if ("merge".equals(type)) {
            result = new MergeSort().sort(arr);
        } else if ("mergeBu".equals(type)) {
            result = new MergeBuSort().sort(arr);
        } else if ("stack".equals(type)){
            result = MinPq.sort(arr);
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
     * 检查数组是否按照从小到大排序
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {
        return isSorted(arr, 0, arr.length - 1);
    }

    /**
     * 检查数组中一部分是否有序
     *
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    public static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (InsertSort.less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
