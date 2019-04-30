package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();
    public static ArrayList<String> file1 = new ArrayList<String>();
    public static ArrayList<String> file2 = new ArrayList<String>();

    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        reader.close();

        BufferedReader file1Reader = new BufferedReader(new FileReader(file1Name));
        while (file1Reader.ready()) {
            file1.add(file1Reader.readLine());
        }
        file1Reader.close();

        BufferedReader file2Reader = new BufferedReader(new FileReader(file2Name));
        while (file2Reader.ready()) {
            file2.add(file2Reader.readLine());
        }
        file2Reader.close();

        while (file1.size() != 0 & file2.size() != 0) {
            if (file1.get(0).equals(file2.get(0))) {
                lines.add(new LineItem(Type.SAME, file1.remove(0)));
                file2.remove(0);
            } else if (file2.size() != 1 && file1.get(0).equals(file2.get(1))) {
                lines.add(new LineItem(Type.ADDED, file2.remove(0)));
            } else if (file1.size() != 1 && file1.get(1).equals(file2.get(0))) {
                lines.add(new LineItem(Type.REMOVED, file1.remove(0)));
            }
        }

        if (file1.size() != 0) {
            lines.add(new LineItem(Type.REMOVED, file1.remove(0)));
        } else if (file2.size() != 0) {
            lines.add(new LineItem(Type.ADDED, file2.remove(0)));
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
