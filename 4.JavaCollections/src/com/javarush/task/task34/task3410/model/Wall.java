package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(150, 80, 50));
        int xAngle = getX() - getWidth() / 2;
        int yAngle = getY() - getHeight() / 2;
        graphics.drawRect(xAngle, yAngle, getWidth(), getHeight());
        graphics.fillRect(xAngle, yAngle, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.drawRect(xAngle, yAngle, getWidth(), getHeight());
        graphics.drawLine(xAngle, yAngle + 4, xAngle + getWidth(), yAngle + 4);
        graphics.drawLine(xAngle, yAngle + 8, xAngle + getWidth(), yAngle + 8);
        graphics.drawLine(xAngle, yAngle + 12, xAngle + getWidth(), yAngle + 12);
        graphics.drawLine(xAngle, yAngle + 16, xAngle + getWidth(), yAngle + 16);
        graphics.drawLine(xAngle + getWidth()/2, yAngle, xAngle + getWidth()/2, yAngle + 4);
        graphics.drawLine(xAngle + getWidth()/4, yAngle + 4, xAngle + getWidth()/4, yAngle + 8);
        graphics.drawLine(xAngle + 3*getWidth()/4, yAngle + 4, xAngle + 3*getWidth()/4, yAngle + 8);
        graphics.drawLine(xAngle + getWidth()/2, yAngle + 8, xAngle + getWidth()/2, yAngle + 12);
        graphics.drawLine(xAngle + getWidth()/4, yAngle + 12, xAngle + getWidth()/4, yAngle + 16);
        graphics.drawLine(xAngle + 3*getWidth()/4, yAngle + 12, xAngle + 3*getWidth()/4, yAngle + 16);
        graphics.drawLine(xAngle + getWidth()/2, yAngle + 16, xAngle + getWidth()/2, yAngle + 20);

    }
}
