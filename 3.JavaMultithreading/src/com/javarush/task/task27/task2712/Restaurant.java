package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Waiter waiter = new Waiter();
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        cook1.addObserver(waiter);
        Thread cookThread1 = new Thread(cook1);
        cookThread1.start();
        Cook cook2 = new Cook("Diego");
        cook2.setQueue(orderQueue);
        cook2.addObserver(waiter);
        Thread cookThread2 = new Thread(cook2);
        cookThread2.start();
        List<Tablet> tabletList = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tabletList.add(tablet);
        }
        Thread thread = new Thread(new RandomOrderGeneratorTask(tabletList,ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            return;
        }
        thread.interrupt();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printArchivedVideoSet();
        directorTablet.printActiveVideoSet();
    }
}
