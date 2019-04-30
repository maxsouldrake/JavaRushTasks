package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File newFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        ArrayList<File> fileList = new ArrayList();

        FileUtils.renameFile(resultFileAbsolutePath, newFile);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        fillFileList(path, fileList);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File file : fileList) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                while (fileInputStream.available()>0) outputStream.write(fileInputStream.read());
                outputStream.write('\n');
            }
        }
        outputStream.close();
    }

    private static void fillFileList(File path, ArrayList<File> fileList) {
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                fillFileList(file, fileList);
                continue;
            }
            if (file.length() > 50) {
                FileUtils.deleteFile(file);
            } else {
                fileList.add(file);
            }
        }
    }



}
