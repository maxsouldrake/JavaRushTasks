package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        try {
            reset();//add your code here - добавьте код тут
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Flyable result;

    public static void reset() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String p = null;
        try {
            p = reader.readLine();
            if (p.equals("helicopter")) {
                result = new Helicopter();
            }
            if (p.equals("plane")) {
                int count = Integer.parseInt(reader.readLine());
                result = new Plane(count);//add your code here - добавьте код тут
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();

    }
}
