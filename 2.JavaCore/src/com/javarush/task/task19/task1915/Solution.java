package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();       //Создаем динамический массив
        PrintStream stream = new PrintStream(outputStream);                     //создаем адаптер к классу PrintStream
        System.setOut(stream);                                                  //Устанавливаем его как текущий System.out
        testString.printSomething();                                            //Вызываем функцию, которая ничего не знает о наших манипуляциях
        System.setOut(consoleStream);
        BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(fileName));
        String result = outputStream.toString();
        byte[] buffer = result.getBytes();
        file.write(buffer);
        System.out.println(result);
        file.close();

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

