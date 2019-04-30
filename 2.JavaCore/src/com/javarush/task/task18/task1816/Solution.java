package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Solution {
    public static void main(String[] args) throws IOException{
        InputStream file = new BufferedInputStream(new FileInputStream(args[0]));
        int count = 0;
        while (file.available() > 0){
            int i = file.read();
            if ((i >= 97 && i <= 122)||(i >= 65 && i <= 90)){
                count++;
            }
        }
        System.out.println(count);
        file.close();
    }
}
