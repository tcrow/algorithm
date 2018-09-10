package org.tcrow.sort;

/**
 * @author tcrow.luo
 */
public class MergeSort implements SortInterface {

    private Comparable[] aux;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return arr;
        }

        if (hi - lo <= 100) {
            new InsertSort().sort(arr, lo, hi);
            return arr;
        }

        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
        return arr;
    }

    private void merge(Comparable[] arr, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

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
