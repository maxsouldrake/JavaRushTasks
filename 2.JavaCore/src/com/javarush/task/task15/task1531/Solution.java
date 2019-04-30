package com.javarush.task.task15.task1531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/* 
Факториал
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());
        reader.close();

        System.out.println(factorial(input));
    }

    public static String factorial(int n) {
        BigInteger f = BigInteger.valueOf(1);
        while (n <= 150) {
            if (n < 0) {
                f = BigInteger.valueOf(0);
            } else if (n == 0) {
                f = f;
            } else {
                for (int i = 1; i < n+1; i++) {
                    f = f.multiply(BigInteger.valueOf(i));//add your code here
                }
            }
            break;
        }

        return f.toString();
    }
}
