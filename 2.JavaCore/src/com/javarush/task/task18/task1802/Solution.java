package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileReader = new FileInputStream(reader.readLine());
        int min = fileReader.read();;
        while (fileReader.available() > 0) //пока остались непрочитанные байты
        {
            int data = fileReader.read();
            if (data < min) {
                min = data;//прочитать очередной байт
            }//добавить его к общей сумме
        }
        System.out.println(min);
        fileReader.close();
    }
}
