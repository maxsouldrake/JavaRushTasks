package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 't', 'z', 'e', 'r', 'o'},
                {'o', 's', 'h', 'm', 'e', 'o'},
                {'u', 'n', 'g', 'r', 'o', 'v'},
                {'r', 'l', 'p', 'r', 'e', 'h'},
                {'p', 'o', 'e', 'o', 'n', 'e'}
        };
        for(Word w : detectAllWords(crossword, "one", "three", "e", "ee", "zero", "vhe", "four", "urp", "eop", "eer", "pre", "glp", "oer"))
            System.out.println(w);

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)\
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> result = new ArrayList<>();


        for (String str : words) {      //      Внешний цикл для прохождения по каждому слову

            // Даллее 8 циклов для проверки каждого направления
            // Направления названы в соответствие с углами тригонометрического круга

            // 0 град
            for (int i = 0; i < crossword.length; i++) {                //  Циклы для прохождения по всем
                for (int j = 0; j < crossword[i].length; j++) {         //  элементам двумерного массива.
                    if (crossword[i][j] == str.charAt(0)) {             //  Проверка на соответствие буквы в кроссворде первой букве искомого слова
                        boolean qw = false;
                        int endx = str.length() + j - 1;                //  Вычисление предполагаемой X координаты конца слова (Y координата в данном направлении не меняется)
                        if (endx < crossword[i].length) {               //  Проверка, не находится ли предполагаемая граница слова за пределами массива
                            for (int m = j; m <= endx; m++) {                   //  Проверка каждого символа в даном направлении
                                if (crossword[i][m] == str.charAt(m-j)) {       //  на соответствие очередному символу искомого слова
                                    qw = true;
                                } else {
                                    qw = false;                                 //  Если символ в массиве не соответствует очередной букве слова
                                    break;                                      //  закрываем цикл, остальные буквы проверять незачем
                                }
                            }
                        }
                        if (qw) {                                               //  Если все буквы соответствуют слову (gw - true)
                            Word w = new Word(str);                             //  Создаем новый объект Word и записываем координаты начала и конца
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx ,i);
                            result.add(w);
                        }
                    }
                }
            }

            //  Аналогично для всех остальных направлений
            // 45 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {     //  Дополнительная проверка что слово длиннее 1 буквы (т.к. все слова из 1 буквы уже записаны в первом цикле)
                        boolean qw = false;
                        int endx = str.length() + j - 1;                            //  Поскольку это направление диагональное
                        int endy = i - str.length() + 1;                            //  тут изменяются уже обе координаты
                        if (endx < crossword[i].length && endy >=0) {
                            for (int m = j, l = i; m <= endx; m++, l--) {
                                if (crossword[l][m] == str.charAt(m-j)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx,endy);
                            result.add(w);
                        }
                    }
                }
            }

            // 90 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endy = i - str.length() + 1;
                        if (endy >=0) {
                            for (int l = i; l >= endy; l--) {
                                if (crossword[l][j] == str.charAt(i-l)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(j,endy);
                            result.add(w);
                        }
                    }
                }
            }

            // 135 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endx = j - str.length() + 1;
                        int endy = i - str.length() + 1;
                        if (endx >= 0 && endy >=0) {
                            for (int m = j, l = i; m >= endx; m--, l--) {
                                if (crossword[l][m] == str.charAt(j-m)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx,endy);
                            result.add(w);
                        }
                    }
                }
            }

            // 180 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endx = j - str.length() + 1;
                        if (endx >= 0) {
                            for (int m = j; m >= endx; m--) {
                                if (crossword[i][m] == str.charAt(j-m)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx ,i);
                            result.add(w);
                        }
                    }
                }
            }

            // 225 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endx = j - str.length() + 1;
                        int endy = str.length() + i - 1;
                        if (endy < crossword.length && endx >=0) {
                            for (int m = j, l = i; l <= endy; m--, l++) {
                                if (crossword[l][m] == str.charAt(l-i)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx,endy);
                            result.add(w);
                        }
                    }
                }
            }

            // 270 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endy = i + str.length() - 1;
                        if (endy < crossword.length) {
                            for (int l = i; l <= endy; l++) {
                                if (crossword[l][j] == str.charAt(l-i)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(j,endy);
                            result.add(w);
                        }
                    }
                }
            }

            // 315 град
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == str.charAt(0) && str.length() > 1) {
                        boolean qw = false;
                        int endx = j + str.length() - 1;
                        int endy = i + str.length() - 1;
                        if (endx < crossword[i].length && endy < crossword.length) {
                            for (int m = j, l = i; m <= endx; m++, l++) {
                                if (crossword[l][m] == str.charAt(m-j)) {
                                    qw = true;
                                } else {
                                    qw = false;
                                    break;
                                }
                            }
                        }
                        if (qw) {
                            Word w = new Word(str);
                            w.setStartPoint(j,i);
                            w.setEndPoint(endx,endy);
                            result.add(w);
                        }
                    }
                }
            }
        }
        return result;  //  Возврат списка всех слов.
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
