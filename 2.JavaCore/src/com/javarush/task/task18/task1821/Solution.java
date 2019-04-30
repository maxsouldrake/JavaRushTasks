package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(args[0]));
        TreeMap<Byte, Integer> map = new TreeMap<Byte, Integer>();
        while (file.available() > 0) {
            byte[] buffer = new byte[file.available()];
            file.read(buffer);
            for (int i = 0; i < buffer.length; i++) {
                if (map.containsKey(buffer[i]))
                    map.put(buffer[i], map.get(buffer[i]) + 1);
                else
                    map.put(buffer[i], 1);
            }
        }
        file.close();
        for (Map.Entry pair : map.entrySet()) {
            System.out.println((char)((byte) pair.getKey()) + " " + pair.getValue());
        }

    }
}
