package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        String file3Name = reader.readLine();
        InputStream file1 = new BufferedInputStream(new FileInputStream(file1Name));
        OutputStream file2 = new BufferedOutputStream(new FileOutputStream(file2Name));
        OutputStream file3 = new BufferedOutputStream(new FileOutputStream(file3Name));
        int size = file1.available()%2 == 0 ? file1.available()/2 : file1.available()/2 + 1;
        while (file1.available() > 0) {
            byte[] buffer1 = new byte[size];
            byte[] buffer2 = new byte[file1.available() - size];
            file1.read(buffer1);
            file1.read(buffer2);
            file2.write(buffer1);
            file3.write(buffer2);
        }
        file1.close();
        file2.close();
        file3.close();
        reader.close();
    }
}
