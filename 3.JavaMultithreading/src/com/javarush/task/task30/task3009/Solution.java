package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []

        System.out.println(new BigInteger("112").toString());
        System.out.println(new BigInteger("112").toString(2));
        System.out.println(new BigInteger("112").toString(3));
        System.out.println(new BigInteger("112").toString(4));
        System.out.println(new BigInteger("112").toString(5));
        System.out.println(new BigInteger("112").toString(6));
        System.out.println(new BigInteger("112").toString(7));
        System.out.println(new StringBuffer(new BigInteger("112").toString(3)).reverse());
    }

    private static Set<Integer> getRadix(String number) {
        Set <Integer> set = new HashSet<>();
        for (int i = 2; i < 37; i ++) {
            try {
                BigInteger bigInteger = new BigInteger(number);
                String big = bigInteger.toString(i);
                String rev = new StringBuffer(big).reverse().toString();
                if (big.equals(rev)) {
                    set.add(i);
                }
            } catch (NumberFormatException e) {
            }

        }
        return set;
    }
}