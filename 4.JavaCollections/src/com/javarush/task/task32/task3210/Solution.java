package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException{
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        byte[] buffer = new byte[text.length()];
        randomAccessFile.seek(number);
        randomAccessFile.read(buffer, 0, buffer.length);

        String newText = new String(buffer);
        String booleanText;

        if (newText.equals(text)) booleanText = "true";
        else booleanText = "false";

        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.write(booleanText.getBytes());

    }
}
