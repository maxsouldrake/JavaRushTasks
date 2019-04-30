package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        ArrayList<String> fileNames = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while (!(s = reader.readLine()).equals("end")) {
                fileNames.add(s);
            }

        Collections.sort(fileNames);

        for (int i = 0; i < fileNames.size(); i++) {
            try (FileInputStream fis = new FileInputStream(fileNames.get(i));
                 FileOutputStream fos = new FileOutputStream(fileNames.get(0).substring(0, fileNames.get(0).lastIndexOf('.')),true)) {
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                fos.write(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
