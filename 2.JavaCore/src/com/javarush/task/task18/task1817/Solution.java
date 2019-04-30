package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.*;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException{
        InputStream file = new BufferedInputStream(new FileInputStream(args[0]));
        int count = 0;
        int size = 0;
        while (file.available() > 0){
            int i = file.read();
            if (i == 32){
                count++;
            }
            size++;
        }
        System.out.println(String.format("%.2f", ((float) count / size) * 100));
        file.close();
    }
}
