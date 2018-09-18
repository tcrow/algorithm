package org.tcrow.sort;

/**
 * @author tcrow.luo
 */
public class MergeSort implements SortInterface {


    private static final int CUTOFF = 100;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(aux, arr, 0, arr.length - 1);
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr, int low, int high) {
        //todo
        return new Comparable[0];
    }

    private Comparable[] sort(Comparable[] aux, Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return arr;
        }

        if (Sort.isSorted(arr, lo, hi)) {
            return arr;
        }

        if (hi - lo <= CUTOFF) {
            new InsertSort().sort(arr, lo, hi);
            return arr;
        }

        int mid = lo + (hi - lo) / 2;
        sort(aux, arr, lo, mid);
        sort(aux, arr, mid + 1, hi);
        merge(aux, arr, lo, mid, hi);
        return arr;
    }

    private void merge(Comparable[] aux, Comparable[] arr, int lo, int mid, int hi) {

        //if arr[mid] <= arr[mid + 1] then arr is sorted,so is
        if (!Sort.less(arr[mid + 1], arr[mid])) {
            return;
        }

        int i = lo, j = mid + 1;

        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

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
