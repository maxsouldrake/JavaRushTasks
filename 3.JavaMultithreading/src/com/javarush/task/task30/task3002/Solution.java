package com.javarush.task.task30.task3002;

import java.math.BigInteger;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int b;
        String a;

        if(s.startsWith("0x")){
            a = s.substring(2);
            b = 16;
        }
        else if(s.startsWith("0b")) {
            a = s.substring(2);
            b = 2;
        }
        else if(s.startsWith("0")){
            a = s.substring(1);
            b = 8;
        }
        else {
            a = s;
            b = 10;
        }
        return String.valueOf(Integer.parseInt(a, b));
    }
}
