package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]));
        StringBuffer buffer = new StringBuffer("");
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] words = line.split(" ");
            for (String word : words)
                if (word.length() > 6)
                    buffer.append(word).append(",");
        }

        buffer.delete(buffer.length()-1,buffer.length());
        fileWriter.write(buffer.toString());
        fileWriter.close();
        fileReader.close();
    }
}
