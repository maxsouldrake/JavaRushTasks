package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable getException(Enum en) {
        if (en != null) {
            if (en instanceof ExceptionApplicationMessage) {
                return new Exception(en.name().charAt(0) + en.name().substring(1).toLowerCase().replace("_", " "));
            }
            else if (en instanceof ExceptionDBMessage) {
                return new RuntimeException(en.name().charAt(0) + en.name().substring(1).toLowerCase().replace("_", " "));
            }
            else if (en instanceof ExceptionUserMessage) {
                return new Error(en.name().charAt(0) + en.name().substring(1).toLowerCase().replace("_", " "));
            }
        }
        return new IllegalArgumentException();
    }
}
