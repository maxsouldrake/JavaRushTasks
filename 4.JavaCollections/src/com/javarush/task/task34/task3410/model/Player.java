package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int xAngle = getX() - getWidth() / 2;
        int yAngle = getY() - getHeight() / 2;
        graphics.setColor(Color.YELLOW);
        graphics.drawOval(xAngle, yAngle, getWidth(), getHeight());
        graphics.fillOval(xAngle, yAngle, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawOval(xAngle + 5, yAngle + 5, 3, 3);
        graphics.fillOval(xAngle + 5, yAngle + 5, 3, 3);
        graphics.drawOval(xAngle + 12, yAngle + 5, 3, 3);
        graphics.fillOval(xAngle + 12, yAngle + 5, 3, 3);
        graphics.drawArc(xAngle + 5, yAngle + 10, 10, 6, 0, -180);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
