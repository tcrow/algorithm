package org.tcrow.sort;

/**
 * @author tcrow.luo
 *         small to big arr merge sort
 */
public class MergeBuSort implements SortInterface {

    private Comparable[] aux;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        for (int sz = 1; sz < arr.length; sz = sz + sz) {
            for (int lo = 0; lo < arr.length - sz; lo += sz + sz) {
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, arr.length - 1));
            }
        }
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr, int low, int high) {
        return new Comparable[0];
    }

    private void merge(Comparable[] arr, int lo, int mid, int hi) {

        //if arr[mid] <= arr[mid + 1] then arr is sorted,so there it no need to do merging
        if (!Sort.less(arr[mid + 1], arr[mid])) {
            return;
        }

//        if (hi - lo <= 100) {
//            new InsertSort().sort(arr, lo, hi);
//        }

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
