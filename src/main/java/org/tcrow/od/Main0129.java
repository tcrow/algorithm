package org.tcrow.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main0129 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            String servers = scanner.nextLine();
            String bad = scanner.nextLine();

            solution(servers, bad);
        }
    }

    private static void solution(String servers, String bad) {
        List<String[]> list = new ArrayList<>();
        List<String> listS = new ArrayList<>();
        List<String> badList = Arrays.asList(bad.split(","));
        for (String string : servers.split(",")) {
            list.add(string.split("-"));
            String a = string.split("-")[0];
            String b = string.split("-")[1];
            if (!listS.contains(a)) {
                listS.add(a);
            }
            if (!listS.contains(b)) {
                listS.add(b);
            }
        }

        for (String s : badList) {
            listS.remove(s);
        }
        List<String> normalList = new ArrayList<>();

        for (String x : listS) {
            if (!broken(list, x, badList)) {
                normalList.add(x);
            }
        }

        int len = normalList.size();
        if (len == 0) {
            System.out.println(",");
        } else {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < len; i++) {
                res.append(normalList.get(i));
                if (i != len - 1) {
                    res.append(",");
                }
            }
            System.out.println(res);
        }
    }

    public static boolean broken(List<String[]> l, String s, List<String> badList) {
        if (badList.contains(s)) {
            return true;
        }
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i)[0].equals(s) && broken(l, l.get(i)[1], badList)) {
                return true;
            }
        }
        return false;
    }
}
