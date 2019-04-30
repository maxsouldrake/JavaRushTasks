package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String s = "incorrect";
            for (int i = 2; i < 37; i++) {
                try {
                    BigInteger big = new BigInteger(args[0], i);
                    s = String.valueOf(i);
                    break;
                } catch (Exception e) {}
            }
            System.out.println(s);
        } catch (Exception e) {}
    }
}