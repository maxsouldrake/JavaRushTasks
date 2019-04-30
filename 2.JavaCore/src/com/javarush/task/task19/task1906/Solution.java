package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        FileWriter fileWriter = new FileWriter(reader.readLine());
        reader.close();
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (fileReader.ready()) {
            list.add(fileReader.read());
        }
        for (int i = 1; i < list.size(); i += 2) {
            fileWriter.write(list.get(i));
        }
        fileReader.close();
        fileWriter.close();
    }
}
