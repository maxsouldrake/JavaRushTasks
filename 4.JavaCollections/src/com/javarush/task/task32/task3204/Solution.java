package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";
        StringBuffer buffer = new StringBuffer();
        buffer.append(upper.charAt((int) (Math.random()*26)));
        buffer.append(lower.charAt((int) (Math.random()*26)));
        buffer.append(digits.charAt((int) (Math.random()*10)));
        for (int i = 0; i < 5; i++) {
            buffer.append((upper + lower + digits).charAt((int) (Math.random()*62)));
        }
        try {
            output.write(buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}