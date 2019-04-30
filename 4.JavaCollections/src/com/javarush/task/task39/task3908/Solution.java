package com.javarush.task.task39.task3908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Ввести строку: ");
            String s = "";
            try {
                s = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(isPalindromePermutation(s));
            if (s.equals("exit")) break;
        }
    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase().replaceAll(" ", "");
        if (s.equals(new StringBuffer(s).reverse().toString())) return true;
        if (s == null || s.length() == 0) return true;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int count = 0;
        for (Character key : map.keySet()) {
            if (s.length() % 2 == 0) {
                if (map.get(key) % 2 != 0) return false;
            } else if (map.get(key) % 2 != 0) count++;
            if (count > 1) return false;
        }
        return true;
    }
}
