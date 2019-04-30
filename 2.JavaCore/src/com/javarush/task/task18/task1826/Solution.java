package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        switch (args[0]) {
            case "-e": {
                BufferedInputStream read = new BufferedInputStream(new FileInputStream(args[1]));
                BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(args[2]));
                while (read.available() > 0) {
                    byte[] buffer = new byte[read.available()];
                    read.read(buffer);
                    for (int i = 0; i < buffer.length; i ++)
                    {
                        buffer[i] = (byte) (buffer[i] - 1);
                    }
                    write.write(buffer);
                }
                read.close();
                write.close();
                break;
            }
            case "-d": {
                BufferedInputStream read = new BufferedInputStream(new FileInputStream(args[1]));
                BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(args[2]));
                while (read.available() > 0) {
                    byte[] buffer = new byte[read.available()];
                    read.read(buffer);
                    for (int i = 0; i < buffer.length; i ++)
                    {
                        buffer[i] = (byte) (buffer[i] + 1);
                    }
                    write.write(buffer);
                }
                read.close();
                write.close();
                break;
            }
        }
    }
}
