package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(file1Name));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2Name));
        StringBuilder builder = new StringBuilder();
        while (fileReader.ready()) {
            builder.append((char)fileReader.read());
        }
        fileReader.close();
        String[] str = builder.toString().split(" ");
        for (String s : str) {
            if (s.matches("[0-9]+"))
                fileWriter.write(s + " ");
        }
        fileWriter.close();
    }
}
