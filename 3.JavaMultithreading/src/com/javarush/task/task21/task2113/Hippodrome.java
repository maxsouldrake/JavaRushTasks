package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private static List<Horse> horses;
    public static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> list) {
        this.horses = list;
    }

    public void print() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void move() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }

    public void run() throws InterruptedException{
        for (int i = 0; i < 100; i ++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public Horse getWinner() {
        double max = -1d;
        Horse winner = new Horse("ы", 0d, 0d);
        for(Horse horse:getHorses()){
            if(horse.getDistance()>max){
                max=horse.getDistance();
                winner=horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public static void main(String[] args) throws InterruptedException{
        game = new Hippodrome(new ArrayList<Horse>());
        game.getHorses().add(new Horse("Fury", 3, 0));
        game.getHorses().add(new Horse("Jack", 3, 0));
        game.getHorses().add(new Horse("Pack", 3, 0));
        game.run();
        game.printWinner();
    }
}
