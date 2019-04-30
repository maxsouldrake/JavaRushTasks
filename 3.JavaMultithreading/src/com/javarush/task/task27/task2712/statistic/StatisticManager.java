package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static synchronized StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public Map<Date, Long> getAdvertisementProfitPerDay() {
        Map<Date, Long> advertisementProfit = new HashMap<>();
        List<EventDataRow> selectedVideos = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        for (EventDataRow eventDataRow : selectedVideos) {
            VideoSelectedEventDataRow video = (VideoSelectedEventDataRow) eventDataRow;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(video.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            Long amount = advertisementProfit.get(date);
            if (amount == null) {
                amount = 0l;
            }
            advertisementProfit.put(date, amount + video.getAmount());
        }
        return advertisementProfit;
    }

    public Map<Date, Map<String, Integer>> getTotalDurationOfOneCookWorked() {
        Map<Date, Map<String, Integer>> totalDurationOfOneCookWorked = new HashMap<>();
        List<EventDataRow> cooks = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        for (EventDataRow eventDataRow : cooks) {
            CookedOrderEventDataRow cooksWork = (CookedOrderEventDataRow) eventDataRow;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cooksWork.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            Map<String, Integer> namesAndDurationOfWork = totalDurationOfOneCookWorked.get(date);
            if (namesAndDurationOfWork == null) {
                namesAndDurationOfWork = new HashMap<>();
                totalDurationOfOneCookWorked.put(date, namesAndDurationOfWork);
            }
            String cookName = cooksWork.getCookName();
            Integer duration = namesAndDurationOfWork.get(cookName);
            if (duration == null) {
                duration = 0;
            }
            namesAndDurationOfWork.put(cookName, duration + cooksWork.getTime());
        }
        return totalDurationOfOneCookWorked;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }
}
