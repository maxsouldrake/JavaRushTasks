package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;
import java.util.Scanner;


public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int s = 0;
        while (true) {
            System.out.print("Введите целое число: ");
            int n = Integer.parseInt(r.readLine());
            s = s + n;
            if (n == -1) {
                break;//напишите тут ваш код
            }
        }
        System.out.println("Сумма всех введенных чисел: "+s);
    }
}
