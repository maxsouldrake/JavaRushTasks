package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public  CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Map<Integer, Integer> getDenominations() {
        return denominations;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
            sum += pair.getKey() * pair.getValue();

        return sum;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        if (getTotalAmount() < expectedAmount) return false;
        else return true;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        HashMap<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);
        ArrayList<Integer> nominals = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            nominals.add(pair.getKey());
        Collections.sort(nominals);
        Collections.reverse(nominals);
        TreeMap<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (Integer nominal : nominals) {
            int key = nominal;
            int value = temp.get(key);
            while (true) {
                if (expectedAmount < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                expectedAmount -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }

        if (expectedAmount > 0)
            throw new NotEnoughMoneyException();
        else
        {
            for (Map.Entry<Integer, Integer> pair : result.entrySet())
                    ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());

            denominations.clear();
            denominations.putAll(temp);
            ConsoleHelper.writeMessage("Transaction was successful!");
        }
        return result;
    }

}
