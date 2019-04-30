package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    static TreeMap<String, Double> map = new TreeMap<>();
    static ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        double max = Double.MIN_VALUE;
        while (file.ready()) {
            String s = file.readLine();
            String[] str = s.split(" ");
            addMap(str[0], Double.parseDouble(str[1]));
        }
        file.close();
        for (Double a : map.values())
            if (max < a)
                max = a;

        //show
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue().equals(max)) {
                System.out.println(pair.getKey());
            }
        }
    }

    private static void addMap(String name, Double value) {
        if (!map.containsKey(name))
            map.put(name,value);
        else
            map.put(name, map.get(name) + value);
    }
}
