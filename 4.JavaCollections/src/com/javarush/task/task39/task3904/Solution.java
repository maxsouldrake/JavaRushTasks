package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/
public class Solution {
    private static int n = 5;
    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        if (n < 0 ) return 0;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        long result = 0;
        long a = 1;
        long b = 1;
        long c = 2;
        long sum = 0;
        for (int i = 0; i < n - 2; i ++) {
            sum = a + b + c;
            a = b;
            b = c;
            c = sum;
            result = c;
        }
        return result;
    }
}

