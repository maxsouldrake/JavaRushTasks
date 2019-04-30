package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();
    public static List<String> list = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] str = line.split("[\\p{P}\\s\\t\\n\\r]");
            int count = 0;
            for (String s : str) {
                for (String word : words) {
                    if (s.trim().equals(word))
                        count += 1;
                }
            }
            if (count == 2)
                System.out.println(line);
        }
        fileReader.close();
    }
}
