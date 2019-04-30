package com.javarush.task.task19.task1911;

/* 
Ридер обертка
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
        testString.printSomething();                                            //Вызываем функцию, которая ничего не знает о наших манипуляциях
        String result = outputStream.toString();                                //Преобразовываем записанные в наш ByteArray данные в строку
        String res = result.toUpperCase();
        System.setOut(consoleStream);
        System.out.println(res);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
