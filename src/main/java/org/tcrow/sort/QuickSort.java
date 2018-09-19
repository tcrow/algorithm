package org.tcrow.sort;

import com.google.common.base.Stopwatch;

import java.util.Random;

/**
 * @author tcrow.luo
 * @date 2018/8/21
 * @description 随机快速排序，屏蔽了出现输入数组完全顺序的情况造成最坏排序结果
 */
public class QuickSort extends AbstractSort {

    /**
     * Fisher–Yates shuffle
     * wiki:https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     *
     * @param arr
     * @return
     */
    private Comparable[] shuffle(Comparable[] arr) {
        int j;
        Random rd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            //generate a random integer 0<=j<=i
            j = rd.nextInt(i + 1);
            exchange(i, j, arr);
        }
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr) {
        //随机交换长度次，打乱原有数组排序规则，保证不会出现顺序数组的概率是个随机数
        Stopwatch stopwatch = Stopwatch.createStarted();
        arr = shuffle(arr);
        stopwatch.stop();
        System.out.println("random array waste times:" + stopwatch.toString());
        return qSort(arr, 0, arr.length - 1);
    }

    private Comparable[] qSort(Comparable[] arr, int low, int high) {
        if (high - low <= CUTOFF) {
            InsertSort.sort(arr, low, high);
            return arr;
        }

        if (high <= low) {
            return arr;
        }

        int pivot = partition(arr, low, high);
        qSort(arr, low, pivot - 1);
        qSort(arr, pivot + 1, high);

        return arr;
    }

    private int partition(Comparable[] arr, int low, int high) {
        int i = low, j = high + 1;
        Comparable k = arr[low];
        while (true) {
            while (less(arr[++i], k)) {
                if (i == high) {
                    break;
                }
            }
            while (less(k, arr[--j])) {

            }
            if (i >= j) {
                break;
            }
            exchange(i, j, arr);
        }
        exchange(low, j, arr);
        return j;
    }
}
