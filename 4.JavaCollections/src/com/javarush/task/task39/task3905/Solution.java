package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        Color[][] image = {{Color.BLUE, Color.ORANGE},{Color.GREEN, Color.INDIGO}};
        for (int j = 0; j < image.length; j ++) {
            for (int i = 0; i < image[0].length ; i ++) {
                System.out.print(image[j][i]+" ");
            }
            System.out.println();
        }

        PhotoPaint photoPaint = new PhotoPaint();
        System.out.println(photoPaint.paintFill(image, 0, 0, Color.BLUE));
        for (int j = 0; j < image.length; j ++) {
            for (int i = 0; i < image[0].length ; i ++) {
                System.out.print(image[j][i]+" ");
            }
            System.out.println();
        }
    }
}
