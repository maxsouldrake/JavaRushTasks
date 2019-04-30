package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    private FileOutputStream original;
    public static String fileName = "C:/tmp/result.txt";

    public AmigoOutputStream(FileOutputStream file) throws FileNotFoundException{
        super(fileName);
        this.original = file;
    }
    public void flush() throws IOException {
        original.flush();
    }

    public void write(int b) throws IOException {
        original.write(b);
    }

    public void write(byte[] b) throws IOException {
        original.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        original.write(b, off, len);
    }
    public void close() throws IOException{
        original.flush();
        write("JavaRush © All rights reserved.".getBytes());
        original.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
