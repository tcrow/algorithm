package org.tcrow.leetcode;

import java.util.Arrays;

public class Test899 {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                sb.append(sb.charAt(0)).deleteCharAt(0);
                if (sb.toString().compareTo(s) < 0) {
                    s = sb.toString();
                }
            }
        } else {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            s = new String(chars);
        }
        return s;
    }

    public static void main(String[] args) {
        Test899 test899 = new Test899();
        System.out.println(test899.orderlyQueue("baaca",3));
    }
}
