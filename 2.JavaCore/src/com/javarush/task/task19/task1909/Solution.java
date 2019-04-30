package com.javarush.task.task19.task1909;

/* 
Замена знаков
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
        String str;
        while (fileReader.ready()) {
            str = fileReader.readLine();
            fileWriter.write(str.replace('.','!'));
        }
        fileReader.close();
        fileWriter.close();
    }
}
