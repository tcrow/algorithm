package org.tcrow.bilibili.class01;

/**
 * 从一个整形数组中找到奇数个数的数字
 * https://www.bilibili.com/video/BV13g41157hK?p=2
 */
public class EvenTimes {

    private static final int[] numbers = new int[]{1, 2, 2, 3, 3, 3, 3, 1, 1};
    private static final int[] numbers2 = new int[]{1, 2, 2, 3, 3, 3, 1, 1, 1, 1, 1, 5};

    /**
     * 找到数组中的唯一奇数次的数字
     *
     * @param arr
     */
    private static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    /**
     * 找到数组中的唯二奇数次的两个
     *
     * @param arr
     */
    private static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        // eor == a ^ b and eor != 0
        // because eor is not zero ,so eor has a one
        int rightOne = eor & (~eor + 1); // pick the right one
        int onlyOne = 0;// eor'
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {// must zero ,can't equal one
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        printOddTimesNum1(numbers);
        printOddTimesNum2(numbers2);
    }
}
