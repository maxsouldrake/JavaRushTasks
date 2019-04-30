package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while (fileReader.ready()) {
            String str = fileReader.readLine();
            char[] ch = str.toCharArray();
            char[] c = new char[ch.length];
            for (int i = ch.length - 1, j = 0; i >=0; i --, j++) {
                c[j] = ch[i];
            }
            str = new String(c);
            System.out.println(str);
        }
        fileReader.close();
    }
}
