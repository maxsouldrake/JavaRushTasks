package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.Man;
import com.javarush.task.task37.task3702.male.TeenBoy;

public interface AbstractFactory {
    public Human getPerson(int age);
}
