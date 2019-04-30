package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        reader.close();

        ArrayList<String> list = new ArrayList<String>();
        InputStream file1Input = new BufferedInputStream(new FileInputStream(file1Name));
        byte[] buffer = new byte[file1Input.available()];
        file1Input.read(buffer);
        file1Input.close();

        String str = new String(buffer);
        for (String i : str.split(" "))
        {
            float x = Float.parseFloat(i);
            list.add(Math.round(x) + " ");
        }

        OutputStream file2Output = new BufferedOutputStream(new FileOutputStream(file2Name));
        for (String j : list)
        {
            file2Output.write(j.getBytes());
        }
        file2Output.close();
    }
}
