package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = null;

        if (date.contains(" ")) {
            sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
            try {
                calendar.setTime(sdf.parse(date));
            } catch (ParseException e) {
            }
            date(calendar);
            time(calendar);
        } else if (date.contains(":")) {
            sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                calendar.setTime(sdf.parse(date));
            } catch (ParseException e) {
            }
            time(calendar);
        } else if (date.contains(".")) {
            sdf = new SimpleDateFormat("dd.MM.yy");
            try {
                calendar.setTime(sdf.parse(date));
            } catch (ParseException e) {
            }
            date(calendar);
        }
    }

    public static void date(Calendar calendar) {
        System.out.println("День: " + calendar.get(Calendar.DATE));
        System.out.println("День недели: " + ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
    }

    public static void time(Calendar calendar) {
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
    }
}
