package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        if (e != null) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void printGetCause(Throwable e) {
        if (e.getCause() != null) printGetCause(e.getCause());
        System.out.println(e.getClass().getName() + ": " + e.getMessage());

    }

    public static void main(String[] args) throws Exception {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
