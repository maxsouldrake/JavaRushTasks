package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException{
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Наберите название блюда из предложенного списка. Для завершения заказа наберите 'exit' ");
        writeMessage(Dish.allDishesToString());
        while (true) {
            String dishToOrder = readString();
            if (dishToOrder.equalsIgnoreCase("exit")) {
                break;
            }
            boolean found = false;
            for (Dish dish : Dish.values()) {
                if (dish.name().equalsIgnoreCase(dishToOrder)) {
                    dishes.add(dish);
                    found = true;
                }
            }
            if (!found) {
                writeMessage("Нет такого блюда");
            }
        }
        return dishes;
    }
}
