package org.tcrow.od;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = Integer.parseInt(scanner.nextLine());
            int N = Integer.parseInt(scanner.nextLine());
            ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                words.add(scanner.nextLine());
            }
            solution(k, words);
        }

    }

    private static void solution(int k, ArrayList<String> words) {
        StringBuilder builder = new StringBuilder();
        builder.append(words.get(k));
        words.remove(k);

        words.sort((w1, w2) -> {
            int len1 = w1.length();
            int len2 = w2.length();
            if (len1 != len2) {
                return len2 - len1;
            } else {
                return w1.compareTo(w2);
            }
        });

        int len;
        do {
            len = builder.length();
            String last = builder.substring(builder.length() - 1);
            for (int i = 0; i < words.size(); i++) {
                String cur = words.get(i);
                if (cur.startsWith(last)) {
                    builder.append(cur);
                    words.remove(cur);
                    break;
                }
            }
        } while (builder.length() != len);

//60,22,41,28,87,95,95,66
//100
        System.out.println(builder);
    }
}