package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(reader.readLine());
        ArrayList<Byte> list=new ArrayList<>();

        while (file.available()>0){
            list.add((byte)file.read());
        }

        HashMap<Byte ,Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i),i);
        }

        TreeMap<Byte, Integer> treeMap = new TreeMap<Byte, Integer>(map);

        if(!map.isEmpty()){
            for (Map.Entry<Byte,Integer> pair: treeMap.entrySet()) {
                System.out.print(pair.getKey() + " ");
            }
        }
        file.close();
    }
}
