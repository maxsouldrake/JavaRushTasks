package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        reader.close();
        BufferedReader fileReader1 = new BufferedReader(new FileReader(name1));
        String file1 = null;
        BufferedReader fileReader2 = new BufferedReader(new FileReader(name2));
        String file2 = null;
        while((file1 = fileReader1.readLine()) != null) {
            allLines.add(file1);
        }
        fileReader1.close();
        while((file2 = fileReader2.readLine()) != null) {
            forRemoveLines.add(file2);
        }
        fileReader2.close();
        new Solution().joinData();
    }

    public void joinData () throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
