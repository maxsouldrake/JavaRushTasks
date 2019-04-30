package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        FileInputStream fileInputStream = new FileInputStream (fileName);
        load(fileInputStream);
        fileInputStream.close();//implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        prop.putAll(properties);
        prop.store(outputStream,"");//implement this method - реализуйте этот метод
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);

        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            properties.put((String)entry.getKey(), (String)entry.getValue());
        }//implement this method - реализуйте этот метод
    }

    public static void main(String[] args) {

    }
}
