package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType) {
        AbstractFactory factory = null;
        if (humanFactoryType == HumanFactoryType.MALE) factory = new MaleFactory();
        if (humanFactoryType == HumanFactoryType.FEMALE) factory = new FemaleFactory();
        return factory;

    }
}
