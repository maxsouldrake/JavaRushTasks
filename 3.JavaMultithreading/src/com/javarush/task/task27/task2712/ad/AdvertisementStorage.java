package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "first", 3000, 15, 5 * 60));
        add(new Advertisement(someContent, "second", 5000, 30, 3 * 60));
        add(new Advertisement(someContent, "third", 1000, 12, 4 * 60));
        add(new Advertisement(someContent, "fourth", 4000, 0, 3 * 60));
        add(new Advertisement(someContent, "fifth", 2000, 10, 5 * 60));
        add(new Advertisement(someContent, "sixth", 5000, 0, 3 * 60));
        add(new Advertisement(someContent, "седьмой", 5000, 17, 4 * 60));
        add(new Advertisement(someContent, "восьмой", 4000, 15, 2 * 60));
        add(new Advertisement(someContent, "девятый", 3000, 15, 3 * 60));
    }

    public static synchronized AdvertisementStorage getInstance() {
        if (instance == null) {
            instance = new AdvertisementStorage();
        }
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
