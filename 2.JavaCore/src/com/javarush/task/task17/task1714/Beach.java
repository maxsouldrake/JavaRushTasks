package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach>{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    @Override
    public synchronized int compareTo(Beach o) {
        int q = 0;
        if (this.distance < o.distance) q++;
        else if (this.distance > o.distance) q--;
        if (this.quality > o.quality) q++;
        else if (this.quality < o.quality) q--;
        return q;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public synchronized static void main(String[] args) {
        Beach beach1 = new Beach("1",1.1f, 2);
        Beach beach2 = new Beach("2",1.5f, 1);
        System.out.println(beach1.compareTo(beach2));
    }
}
