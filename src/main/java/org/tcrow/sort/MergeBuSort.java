package org.tcrow.sort;

/**
 * @author tcrow.luo
 *         small to big arr merge sort
 */
public class MergeBuSort extends AbstractSort {

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] result = arr.clone();
        Comparable[] aux = new Comparable[result.length];
        for (int sz = 1; sz < result.length; sz = sz + sz) {
            for (int lo = 0; lo < result.length - sz; lo += sz + sz) {
                merge(aux, result, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, result.length - 1));
            }
        }
        return result;
    }

    private void merge(Comparable[] aux, Comparable[] arr, int lo, int mid, int hi) {

        //if arr[mid] <= arr[mid + 1] then arr is sorted,so there it no need to do merging
        if (!less(arr[mid + 1], arr[mid])) {
            return;
        }

        if (hi - lo <= CUTOFF) {
            InsertSort.sort(arr, lo, hi);
        }

        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        commonMerge(aux, arr, lo, mid, hi);

    }

}
