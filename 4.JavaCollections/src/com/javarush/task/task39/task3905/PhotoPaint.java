package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (x >= image[0].length || y >= image.length || y < 0 || x < 0) return false;
        if (image[y][x] == desiredColor || desiredColor == null) return false;
        image[y][x] = desiredColor;
        return true;
    }
}
