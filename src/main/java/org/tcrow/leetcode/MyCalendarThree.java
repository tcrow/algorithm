package org.tcrow.leetcode;

import java.util.TreeMap;

/**
 * 差分数组
 */
public class MyCalendarThree {
    private TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        System.out.println(myCalendarThree.book(10, 20)); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
        System.out.println(myCalendarThree.book(50, 60)); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
        System.out.println(myCalendarThree.book(10, 40)); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
        System.out.println(myCalendarThree.book(5, 15)); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
        System.out.println(myCalendarThree.book(5, 10)); // 返回 3
        System.out.println(myCalendarThree.book(25, 55)); // 返回 3

    }

    public MyCalendarThree() {
        this.treeMap = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        this.treeMap.put(startTime, this.treeMap.getOrDefault(startTime, 0) + 1);
        this.treeMap.put(endTime, this.treeMap.getOrDefault(endTime, 0) - 1);
        int ans = 0;
        int maxBook = 0;
        for (Integer value : treeMap.values()) {
            maxBook += value;
            ans = Math.max(ans, maxBook);
        }
        return ans;
    }
}
