package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements ATableInterface{
        private ATableInterface orig;

        public TableInterfaceWrapper(ATableInterface obj) {
            super();
            this.orig = obj;
        }

        public void setModel(List rows) {
            System.out.println(rows.size());
            orig.setModel(rows);
        }

        public String getHeaderText() {
            return orig.getHeaderText().toUpperCase();
        }

        public void setHeaderText(String newHeaderText) {
            orig.setHeaderText(newHeaderText);
        }
    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}