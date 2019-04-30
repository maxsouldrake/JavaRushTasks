package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(1162261467));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(9));
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        int k = 1;
        boolean is = false;
        for (int i = 0; i < 20; i++) {
            k *= 3;
            if (k == n) is = true;
        }
        return is;
    }
}
