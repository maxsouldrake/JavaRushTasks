package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileReader = new FileInputStream(reader.readLine());
        int max = 0;
        while (fileReader.available() > 0) //пока остались непрочитанные байты
        {
            int data = fileReader.read();
            if (data > max) {
                max = data;//прочитать очередной байт
            }//добавить его к общей сумме
        }
        System.out.println(max);
        fileReader.close();
    }
}
