package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        int xAngle = getX() - getWidth()/2;
        int yAngle = getY() - getHeight()/2;
        graphics.draw3DRect(xAngle, yAngle, getWidth(), getHeight(), false);
        graphics.fill3DRect(xAngle, yAngle, getWidth(), getHeight(), false);
    }
}
