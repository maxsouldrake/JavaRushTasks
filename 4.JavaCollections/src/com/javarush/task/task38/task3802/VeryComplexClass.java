package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.BufferedReader;
import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("c.txt"));
    }

    public static void main(String[] args) {

    }
}
