package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] c = s.toCharArray();
        int [] num = new int[c.length];
        int n = 0;
        for (int i = 0; i < c.length; i++) {
            switch (c[i]) {
                case 'I': {
                    num[i] = 1;
                    break;
                }
                case 'V': {
                    num[i] = 5;
                    break;
                }
                case 'X': {
                    num[i] = 10;
                    break;
                }
                case 'L': {
                    num[i] = 50;
                    break;
                }
                case 'C': {
                    num[i] = 100;
                    break;
                }
                case 'D': {
                    num[i] = 500;
                    break;
                }
                case 'M': {
                    num[i] = 1000;
                    break;
                }
            }
        }
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i + 1] / num[i] == 2 || num[i + 1] / num[i] == 5 || num[i + 1] / num[i] == 10) num[i] = - num[i];
        }
        for (int i = 0; i < num.length; i++) {
            n += num[i];
        }
        return n;
    }
}
