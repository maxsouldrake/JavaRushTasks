package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    private static long[][] matrix;
    private static long[] maxs;
    private static long[] mins;
    private static ArrayList<Long> resultList = new ArrayList<Long>(); //  список с найденными числами

    public static long[] getNumbers(long N) {
        long[] result = null;
        int digNumb = getDigNumb(N); //  определяем количество цифр в N

        matrix = new long[10][digNumb]; // матрица с цифрами, возведенными в степень от 1 до digNumb
        maxs = new long[digNumb]; // массив с максимальными значениями для соответствующего разряда
        mins = new long[digNumb]; // массив с минимальными значениями для соответствующего разряда
        // заполняем matrix, maxs, mins
        for (int j = 0; j < digNumb; j++) {
            mins[j] = 1*pow(10, j);
            maxs[j] = 1*pow(10, j+1) - 1;
            for (int i = 0; i < 10; i++) {
                matrix[i][j] = pow(i, j + 1);
            }
        }
        maxs[digNumb - 1] = N - 1; // максимальное число, для которого будет осуществлятся поиск (меньше N)
        // ищем числа Армстронга для каждого порядка отдельно, начиная с 1
        for (int r = 1; r <= digNumb ; r++) {
            proc(0, 1, r);
        }
        Collections.sort(resultList);
        ArrayList<Long> res = new ArrayList<Long>();
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i) >= N) break;
            res.add(resultList.get(i));
        }
        if (res.size() > 0) {
            result = new long[res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }
        }

        return result;
    }
    //рекурсивно перебираем все числа. first - наименьшая цифра для текущего разряда;
    // digPos - текущий разряд, позиция цифры в числе; numb - число старшего разряда
    private static void proc(long numb, int first, int digPos) {
        for (int i = first; i < 10; i++) {
            long number = i * pow(10, digPos - 1) + numb;
            // если текущее число превысило максимальное для Long (стало меньше ноля), выходим
            if (number < 0) return;
            // если из number путем перестановки его цифр можно получить число Армстронга, находим его
            long arm = getArmst(number);
            if (arm >= 0 && !resultList.contains(arm))
                resultList.add(arm);
            if (digPos > 1) proc(number, i, digPos - 1);
        }
        return;
    }

    // если сумма цифр числа N, возведенных в степень порядка числа N, является числом Армстронга того же порядка, что
    // и N, получаем его, иначе возвращается -1. Если найденное число Армстронга не меньше числа N,
    // также возвращается -1
    private static long getArmst (long numb) {
        long sum = 0;
        int digNumb = getDigNumb(numb); // количество цифр в Numb
        // считаем sum - сумму всех цифр, возведенных в степень порядка числа
        do {
            sum += matrix[(int) (numb % 10)][digNumb - 1];
        } while ((numb /= 10) > 0);
        // если sum больше максимального или минимального числа для данного порядка, возвращаем -1
        if(sum > maxs[digNumb - 1] || sum < mins[digNumb - 1] || sum < 0) return -1;
        // проверяем, является ли sum числом Армстронга, если да, возвращаем его, если нет, возвращаем -1
        long sum1 = 0;
        for (long i = sum; i > 0 ; i = i/10) {
            sum1 += matrix[(int) (i % 10)][digNumb - 1];
        }
        if (sum1 != sum) return -1;
        return sum;
    }

    //количество цифр в numb
    private static int getDigNumb (long numb) {
        long temp = 0L;
        int digNumb = 1;
        while ((temp = (temp << 3) + (temp << 1) + 9L) < numb && temp > 0)
            digNumb++;
        return digNumb;
    }

    // возведение в степень exp числа num
    public static long pow (int num, int exp) {
        long l = 1;
        for (int i = 0; i < exp; i++)
            l *= (long)num;
        return l;
    }

    public static void main(String[] args) {
        long [] result = getNumbers(999999999);
        System.out.println(Arrays.toString(result));
    }
}
