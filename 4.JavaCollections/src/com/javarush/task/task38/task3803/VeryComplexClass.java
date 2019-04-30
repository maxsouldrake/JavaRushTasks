package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object c = new Character('c');
        System.out.println((Byte) c);
    }

    public void methodThrowsNullPointerException() {
        Object o = null;
        o.toString();

    }

    public static void main(String[] args) {

    }
}
