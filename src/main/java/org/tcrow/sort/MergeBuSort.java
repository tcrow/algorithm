package org.tcrow.sort;

/**
 * @author tcrow.luo
 *         small to big arr merge sort
 */
public class MergeBuSort extends AbstractSort {

    private static final int CUTOFF = 100;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        for (int sz = 1; sz < arr.length; sz = sz + sz) {
            for (int lo = 0; lo < arr.length - sz; lo += sz + sz) {
                merge(aux, arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, arr.length - 1));
            }
        }
        return arr;
    }

    private void merge(Comparable[] aux, Comparable[] arr, int lo, int mid, int hi) {

        //if arr[mid] <= arr[mid + 1] then arr is sorted,so there it no need to do merging
        if (!Sort.less(arr[mid + 1], arr[mid])) {
            return;
        }

        if (hi - lo <= CUTOFF) {
            InsertSort.sort(arr, lo, hi);
        }

        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        commonMerge(aux, arr, lo, mid, hi);

    }

}
