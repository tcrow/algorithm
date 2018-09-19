package org.tcrow.sort;

/**
 * @author tcrow.luo
 */
public class MergeSort extends AbstractSort {

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] aux = arr.clone();
        sort(aux, arr, 0, arr.length - 1);
        return arr;
    }

    public static Comparable[] sort(Comparable[] aux, Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return arr;
        }

        if (Sort.isSorted(arr, lo, hi)) {
            return arr;
        }

        if (hi - lo <= CUTOFF) {
            InsertSort.sort(arr, lo, hi);
            return arr;
        }

        int mid = lo + (hi - lo) / 2;
        sort(aux, arr, lo, mid);
        sort(aux, arr, mid + 1, hi);
        merge(aux, arr, lo, mid, hi);
        return arr;
    }

    private static void merge(Comparable[] aux, Comparable[] arr, int lo, int mid, int hi) {
        //if arr[mid] <= arr[mid + 1] then arr is sorted,so is
        if (!less(arr[mid + 1], arr[mid])) {
            return;
        }

        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        commonMerge(aux, arr, lo, mid, hi);

    }

}
