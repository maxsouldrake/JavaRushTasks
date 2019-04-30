package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();
    public static ArrayList<String> files = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while (true) {
            s = reader.readLine();
            if (s.equals("exit"))
                break;

            ReadThread thread = new ReadThread(s);
            thread.start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;
        private Byte b;
        public ReadThread(String fileName) {
            this.fileName = fileName;//implement constructor body
        }

        public void run() {
            HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();

            try {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(this.fileName));
                while (file.available() > 0) {
                    byte[] buffer = new byte[file.available()];
                    file.read(buffer);

                    for (byte i : buffer) {
                        if (map.containsKey(i))
                            map.put(i, map.get(i) + 1);
                        else
                            map.put(i, 1);
                    }
                }
                file.close();
            } catch (Exception e) {}//catch (Exception e) {}

            //Find Byte
            int max = 0;
            for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
                if (max < pair.getValue()) {
                    this.b = pair.getKey();
                    max = pair.getValue();
                }
            }

            synchronized (resultMap) {
                resultMap.put(this.fileName, new Integer(this.b));
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
