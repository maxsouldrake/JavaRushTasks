package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    static TreeMap<String, Double> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        while (file.ready()) {
            String s = file.readLine();
            String[] str = s.split(" ");
            addMap(str[0], Double.parseDouble(str[1]));
        }
        file.close();
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            System.out.println(pair.getKey()+" "+pair.getValue());
        }
    }

    private static void addMap(String name, Double value) {
        if (!map.containsKey(name))
            map.put(name,value);
        else
            map.put(name, map.get(name) + value);
    }
}
