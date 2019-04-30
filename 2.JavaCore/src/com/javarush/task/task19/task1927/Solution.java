package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();       //Создаем динамический массив
        PrintStream stream = new PrintStream(outputStream);                     //создаем адаптер к классу PrintStream
        System.setOut(stream);                                                  //Устанавливаем его как текущий System.out
        testString.printSomething();
        String result = outputStream.toString();
        System.setOut(consoleStream);
        int count = 0;
        for (String s : result.split("\n")) {
            System.out.println(s);
            count++;
            if (count % 2 == 0) {
                System.out.println("JavaRush - курсы Java онлайн");
            }
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
