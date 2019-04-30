package com.javarush.task.task39.task3909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String first = "";
            String second = "";
            try {
                System.out.println("Первая строка: ");
                first = bufferedReader.readLine();
                System.out.println("Вторая строка: ");
                second = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(isOneEditAway(first, second));
            if (first.equals("exit")) break;
        }

    }

    public static boolean isOneEditAway(String first, String second) {
        if (first.equals(second)) return true;
        if (first == null || second == null) return false;
        if (Math.abs(first.length() - second.length()) > 1) return false;
        if (first.length() == second.length()) {
            int count = 0;
            for (int i = 0; i < first.length(); i ++) {
                if (first.charAt(i) != second.charAt(i)) count ++;
            }
            if (count > 1) return false;
            else return true;
        }
        if (first.length() - second.length() == 1) {
            if (first.contains(second)) return true;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    first = first.substring(0, i) + first.substring(i + 1);
                }
            }
            if (first.equals(second)) return true;
            else return false;
        }
        if (second.length() - first.length() == 1) {
            if (second.contains(first)) return true;
            for (int i = 0; i < second.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    second = second.substring(0, i) + second.substring(i + 1);
                }
            }
            if (second.equals(first)) return true;
            else return false;
        }
        return true;
    }
}
