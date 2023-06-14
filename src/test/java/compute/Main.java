package compute;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(process(input));
    }

    private static String process(String input) {
        Map<Integer, Character> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char chars = input.charAt(i);
            if (!(chars >= 'a' && chars <= 'z') && !(chars >= 'A' && chars <= 'Z')) {
                map.put(i, chars);
            } else {
                String str = String.valueOf(chars);
                String upStr = str.toUpperCase(Locale.ROOT);
                if (map2.containsKey(upStr)) {
                    map2.put(upStr, map2.get(upStr) + str);
                } else {
                    map2.put(upStr, str);
                }
            }
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        index = processChars(sb,map,index);
        for (int i = 0; i < 26; i++) {
            if (map2.containsKey(String.valueOf((char) ('A' + i)))) {
                String value = map2.get(String.valueOf((char) ('A' + i)));
                String[] arr = value.split("");
                for (String str : arr) {
                    sb.append(str);
                    index++;
                    index = processChars(sb,map,index);
                }
            }
        }
        while (sb.length() < input.length()) {
            sb.append(map.get(index));
            index++;
        }
        return sb.toString();
    }

    private static int processChars(StringBuilder sb,Map<Integer, Character> map,int index){
        while (map.containsKey(index)){
            sb.append(map.get(index));
            index++;
        }
        return index;
    }
}