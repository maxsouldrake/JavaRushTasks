package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new InfThread());
        threads.add(new IntThread());
        threads.add(new YesThread());
        threads.add(new MesThread());
        threads.add(new BufThread());
    }

    public static void main(String[] args) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static class InfThread extends Thread {
        public void run() {
            while (true) {}
        }
    }
    public static class IntThread extends Thread {
        public void run() {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
    public static class YesThread extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {}

    }
    }
    public static class MesThread extends Thread implements Message{
        private boolean stop = false;

        @Override
        public void run() {
            while (stop == false) {
            }
        }

        @Override
        public void showWarning() {
            stop = true;
        }
    }
    public static class BufThread extends Thread {

        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int summ = 0;
            String s = null;
            while (true) {
                try {
                    s = reader.readLine();
                } catch (IOException e) {}
                if (s.equals("N")) break;
                summ += Integer.parseInt(s);
            }
            System.out.println(summ);
        }
    }
}