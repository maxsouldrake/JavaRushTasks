package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes type) {
        if (type == ImageTypes.JPG) return new JpgReader();
        if (type == ImageTypes.PNG) return new PngReader();
        if (type == ImageTypes.BMP) return new BmpReader();
        else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
