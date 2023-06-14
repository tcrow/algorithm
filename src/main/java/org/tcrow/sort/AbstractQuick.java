package org.tcrow.sort;

import com.google.common.base.Stopwatch;

/**
 * @author tcrow.luo
 */
public abstract class AbstractQuick extends AbstractSort{
    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] result = arr.clone();
        //随机交换长度次，打乱原有数组排序规则，保证不会出现顺序数组的概率是个随机数
        Stopwatch stopwatch = Stopwatch.createStarted();
        result = shuffle(result);
        stopwatch.stop();
        System.out.println("random array waste times:" + stopwatch.toString());
        return qSort(result, 0, result.length - 1);
    }

    protected Comparable[] qSort(Comparable[] arr, int low, int high) {
        if (high - low <= CUTOFF) {
            InsertSort.sort(arr, low, high);
            return arr;
        }

        int pivot = partition(arr, low, high);
        qSort(arr, low, pivot - 1);
        qSort(arr, pivot + 1, high);

        return arr;
    }

    protected int partition(Comparable[] arr, int low, int high) {
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
