package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public interface SortInterface {

    /**
     * 对整形数组进行排序
     * @param arr
     * @return
     */
    Comparable[] sort(Comparable[] arr);

    /**
     * 对数组中的从low到high的数值进行排序
     * @param arr
     * @param low
     * @param high
     * @return
     */
    Comparable[] sort(Comparable[] arr,int low,int high);

}
