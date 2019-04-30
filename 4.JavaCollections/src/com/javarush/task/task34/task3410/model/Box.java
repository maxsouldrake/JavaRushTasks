package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(200, 150, 50));
        int xAngle = getX() - getWidth() / 2;
        int yAngle = getY() - getHeight() / 2;

        graphics.draw3DRect(xAngle, yAngle, getWidth(), getHeight(), true);
        graphics.fill3DRect(xAngle, yAngle, getWidth(), getHeight(), true);
        graphics.setColor(Color.WHITE);
        graphics.drawLine(xAngle, yAngle, xAngle + getWidth(), yAngle + getHeight());
        graphics.drawLine(xAngle, yAngle +getHeight(), xAngle + getWidth(), yAngle);

    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
