package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream file1 = new BufferedOutputStream(new FileOutputStream(reader.readLine()));
        InputStream file2 = new BufferedInputStream(new FileInputStream(reader.readLine()));
        InputStream file3 = new BufferedInputStream(new FileInputStream(reader.readLine()));
        while (file2.available() > 0) {
            byte[] buffer2 = new byte[file2.available()];
            file2.read(buffer2);
            file1.write(buffer2);
        }
        file2.close();
        while (file3.available() > 0) {
            byte[] buffer3 = new byte[file3.available()];
            file3.read(buffer3);
            file1.write(buffer3);
        }
        file1.close();
        file3.close();
    }
}
