package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {
        ArrayList<String> tok = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            tok.add(token);
        }
        String[] result = new String[tok.size()];
        for (int i = 0; i < tok.size(); i++) {
            result[i] = tok.get(i);
        }
        return result;
    }
}
