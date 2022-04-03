package org.tcrow.bilibili.class01;

import org.tcrow.util.RandomUtil;

import java.util.Arrays;

public class BinarySearch {

    private static boolean binarySearch(Integer[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;// = (low + high) / 2
            int midVal = array[mid];
            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else
                return true; // key found
        }
        return false; // not found
    }

    private static boolean binarySearch2(Integer[] array, int target) {
        int low = -1;
        int high = array.length;
        while (low + 1 != high) {
            int mid = (low + high) >>> 1;// = (low + high) / 2
            int midVal = array[mid];
            if (midVal < target)
                low = mid;
            else if (midVal > target)
                high = mid;
            else
                return true; // key found
        }
        return false; // not found
    }


    public static boolean find(int target, Integer[][] array) {
        if (array.length == 0) {
            return false;
        }
        for (Integer[] arr : array) {
//            if (Arrays.binarySearch(arr, target) >= 0) {
//                return true;
//            }
            if (binarySearch(arr, target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Integer[][] array = new Integer[100][];
        for (int i = 0; i < 100; i++) {
            array[i] = RandomUtil.generateRandomArray(100, 100);
        }
        for (Integer[] integers : array) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : integers) {
                sb.append(integer + " ");
            }
            System.out.println(sb.toString());
        }
        boolean flag = find(50, array);
        System.out.println(flag);
        assert true == flag;

    }
}
