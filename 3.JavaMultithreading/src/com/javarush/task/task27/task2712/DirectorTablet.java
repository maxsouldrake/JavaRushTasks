package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    void printAdvertisementProfit() {
        TreeMap<Date, Long> advertisementProfit = new TreeMap<>(Collections.reverseOrder());
        advertisementProfit.putAll(StatisticManager.getInstance().getAdvertisementProfitPerDay());
        Double totalAmount = 0d;
        for(Map.Entry<Date, Long> entry : advertisementProfit.entrySet()) {
            Double amount = entry.getValue()/100d;
            String n = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).toString();
            n = new DecimalFormat("#.##").format(Double.valueOf(n));
            ConsoleHelper.writeMessage(String.format("%s - %s", sdf.format(entry.getKey()), n));
            totalAmount += amount;
        }
        String n = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP).toString();
        n = new DecimalFormat("#.##").format(Double.valueOf(n));
        ConsoleHelper.writeMessage(String.format("Total - %s", n));
    }

    void printCookWorkloading() {
        TreeMap<Date, Map<String, Integer>> cookWorkloading = new TreeMap<>(Collections.reverseOrder());
        cookWorkloading.putAll(StatisticManager.getInstance().getTotalDurationOfOneCookWorked());
        Map<String, Integer> cooksWorkloadingPerDay;
        for (Map.Entry<Date, Map<String, Integer>> loading : cookWorkloading.entrySet()) {
            Date date = loading.getKey();
            cooksWorkloadingPerDay = loading.getValue();
            ConsoleHelper.writeMessage(sdf.format(date));
            for (Map.Entry<String, Integer> entry : cooksWorkloadingPerDay.entrySet()) {
                String cookName = entry.getKey();
                int workLoading = entry.getValue();
                if (workLoading > 0) {
                    ConsoleHelper.writeMessage(String.format("%s - %d min", cookName, workLoading));
                }
            }
            ConsoleHelper.writeMessage("\n");
        }
    }

    void printActiveVideoSet() {
        TreeMap<String, Integer> activeVideoSet = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement : StatisticAdvertisementManager.getInstance().getActiveVideoSet()) {
            activeVideoSet.put(advertisement.getName(), advertisement.getHits());
        }
        for (Map.Entry<String, Integer> entry : activeVideoSet.entrySet()) {
            String name = entry.getKey();
            int hits = entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %d", name, hits));
        }
    }

    void printArchivedVideoSet() {
        TreeMap<String, Integer> archivedVideoSet = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement : StatisticAdvertisementManager.getInstance().getArchivedVideoSet()) {
            archivedVideoSet.put(advertisement.getName(), advertisement.getHits());
        }
        for (Map.Entry<String, Integer> entry : archivedVideoSet.entrySet()) {
            String name = entry.getKey();
            ConsoleHelper.writeMessage(String.format(name));
        }
    }
}
