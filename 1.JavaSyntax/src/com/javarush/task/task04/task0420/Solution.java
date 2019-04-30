package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        if (a >= b && b >= c)
            System.out.print(a + " " + b + " " + c);
        else if (a >= b && c >= b)
            System.out.print(b + " " + a + " " + c);
        else if (c >= b && c >= a)
            System.out.print(c + " " + b + " " + a);
    }
}

