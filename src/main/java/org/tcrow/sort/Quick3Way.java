package org.tcrow.sort;

/**
 * @author tcrow.luo
 *         三路快速排序算法，当数据中较多重复数据时，效率大大高于普通快速排序
 */
public class Quick3Way extends AbstractQuick {

    @Override
    protected Comparable[] qSort(Comparable[] arr, int low, int high) {
        if (high - low <= CUTOFF) {
            InsertSort.sort(arr, low, high);
            return arr;
        }

        if (high <= low) {
            return arr;
        }

        int lt = low, i = low + 1, gt = high;

        Comparable v = arr[low];

        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) {
                exchange(lt++, i++, arr);
            } else if (cmp > 0) {
                exchange(i, gt--, arr);
            } else {
                i++;
            }
        }
        qSort(arr, low, lt - 1);
        qSort(arr, gt + 1, high);
        return arr;
    }

}
