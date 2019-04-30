package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static synchronized StatisticAdvertisementManager getInstance() {
        if (instance == null) {
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }

    public List<Advertisement> getActiveVideoSet() {
        List<Advertisement> activeVideoSet = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() > 0) {
                activeVideoSet.add(advertisement);
            }
        }
        return  activeVideoSet;
    }

    public List<Advertisement> getArchivedVideoSet() {
        List<Advertisement> archivedVideoSet = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() == 0) {
                archivedVideoSet.add(advertisement);
            }
        }
        return  archivedVideoSet;
    }
}
