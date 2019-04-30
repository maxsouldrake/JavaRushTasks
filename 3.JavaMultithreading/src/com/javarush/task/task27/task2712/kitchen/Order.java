package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException{
        this.tablet = tablet;
        this.initDishes();
    }

    public int getTotalCookingTime() {
        int totalCookingTime = 0;
        for (Dish dish: dishes) {
            totalCookingTime += dish.getDuration();
        }
        return totalCookingTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s", dishes, tablet);
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    protected void initDishes() throws IOException{
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
