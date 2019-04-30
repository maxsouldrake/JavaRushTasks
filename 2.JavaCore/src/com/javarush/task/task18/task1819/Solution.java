package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        reader.close();

        InputStream read1 = new BufferedInputStream(new FileInputStream(file1Name));
        byte[] buffer1 = new byte[read1.available()];
        read1.read(buffer1);
        read1.close();

        InputStream read2 = new BufferedInputStream(new FileInputStream(file2Name));
        byte[] buffer2 = new byte[read2.available()];
        read2.read(buffer2);
        read2.close();

        OutputStream write1 = new BufferedOutputStream(new FileOutputStream(file1Name));
        write1.write(buffer2);
        write1.close();

        OutputStream write2 = new BufferedOutputStream(new FileOutputStream(file1Name));
        write2.write(buffer1);
        write2.close();
    }
}
