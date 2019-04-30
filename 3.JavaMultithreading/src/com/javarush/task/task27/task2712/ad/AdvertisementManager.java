package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> optimalVideoSet = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException{
        List<Advertisement> suitVideos = new ArrayList<>();
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long different = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (different != 0) {
                    return (int) different;
                } else {
                    return (int) (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                }
            }
        });
        for (Advertisement video : storage.list()) {
            if (video.getDuration() <= timeSeconds && video.getHits() > 0) {
                suitVideos.add(video);
            }
        }
        recursion(suitVideos);
        if (getOptimalVideoSet().isEmpty()) throw new NoVideoAvailableException();
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(getOptimalVideoSet(),
                getTotalAmount(getOptimalVideoSet()),
                getTotalDuration(getOptimalVideoSet())));
        for (Advertisement video : getOptimalVideoSet()) {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", video.getName(),
                    video.getAmountPerOneDisplaying(),
                    video.getAmountPerOneDisplaying() * 1000 / video.getDuration()));
            video.revalidate();
        }

    }
    private int getTotalDuration(List<Advertisement> videos) {
        int totalTime = 0;
        for (Advertisement video : videos) {
            totalTime += video.getDuration();
        }
        return totalTime;
    }

    private long getTotalAmount(List<Advertisement> videos) {
        long totalAmount = 0;
        for (Advertisement video : videos) {
            totalAmount += video.getAmountPerOneDisplaying();
        }
        return totalAmount;
    }

    private void checkList(List<Advertisement> videos) {
        if (optimalVideoSet == null) {
            if (getTotalDuration(videos) <= timeSeconds) {
                optimalVideoSet = videos;
            }
        } else {
            if (getTotalDuration(videos) <= timeSeconds) {
                if (getTotalAmount(videos) > getTotalAmount(optimalVideoSet)) {
                    optimalVideoSet = videos;
                }
                if (getTotalAmount(videos) == getTotalAmount(optimalVideoSet)) {
                    if (getTotalDuration(videos) > getTotalDuration(optimalVideoSet)) {
                        optimalVideoSet = videos;
                    }
                    if (getTotalDuration(videos) == getTotalDuration(optimalVideoSet) && videos.size() < optimalVideoSet.size()) {
                        optimalVideoSet = videos;
                    }
                }
            }
        }
    }

    private void recursion(List<Advertisement> videos) {
        if (videos.size() > 0)
            checkList(videos);
        for (int i = 0; i < videos.size(); i++) {
            List<Advertisement> newList = new ArrayList<>(videos);
            newList.remove(i);
            recursion(newList);
        }
    }

    private List<Advertisement> getOptimalVideoSet() {
        return optimalVideoSet;
    }
}
