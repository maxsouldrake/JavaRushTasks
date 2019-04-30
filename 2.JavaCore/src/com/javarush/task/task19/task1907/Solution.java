package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileName);
        String str = "";
        while (fileReader.ready()) {
            str += (char) fileReader.read();
        }
        str = str.replaceAll("[\\p{Punct}+|\\s]"," ");
        String[] parts = str.split(" ");
        int count = 0;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("world")){
                count ++;
            }
        }

        System.out.println(count);


        fileReader.close();
    }
}
