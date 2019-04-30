package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream file1 = new BufferedInputStream(new FileInputStream(reader.readLine()));
        OutputStream file2 = new BufferedOutputStream(new FileOutputStream(reader.readLine()));
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (file1.available() > 0) {
            list.add(file1.read());
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            file2.write(list.get(i));
        }
        file1.close();
        file2.close();
        reader.close();

    }
}
