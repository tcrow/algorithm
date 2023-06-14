package org.tcrow.od;

public class RandomUtils {
    public static int[] generate(int len, int max) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static int[] generate2(int len, int max) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = (int) (Math.random() * max) + 1;
            } else {
                arr[i] = (int) (1 - (Math.random() * max));
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] generate = generate(100, 100);
        for (int i : generate) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }
}
