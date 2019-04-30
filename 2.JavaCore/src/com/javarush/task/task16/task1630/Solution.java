package com.javarush.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static {
        try{
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {}

    } //add your code here - добавьте код тут

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();//add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private String fileName;
        private String fileContent = "";
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }
        public String getFileContent() {
            return fileContent;
        }
        public void run() {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                while (fileReader.ready()) {
                    fileContent += fileReader.readLine() + " ";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }//add your code here - добавьте код тут
}
