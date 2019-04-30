package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Solution solution = new Solution();
        String s;
        s = "sin(45) - cos(45)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);
        s = "0.305";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        s = "10-2^(2-1+1)";
        System.out.print(s + " expected output 6 4 actually ");
        solution.recursion(s, 0);
        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);
        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);
        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);
        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);
        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);
        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        s = "-(-22+22*2)";
        System.out.print(s + " expected output -22 4 actually ");
        solution.recursion(s, 0);
        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);
        s = "-(-2^(-2))+2+(-(-2^(-2)))";
        System.out.print(s + " expected output 2.5 10 actually ");
        solution.recursion(s, 0);
        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);
        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);
        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);
        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);
        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);
        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);
        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recursion(s, 0);
        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);
        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);
    }

    public void recursion(final String expression,int countOperation) {
        String express;
        if (countOperation == 0) {
            express = expression.toLowerCase().replaceAll(" ", "");
            countOperation = countOperations(express);
            if (countOperation == 0) {
                express = express.replaceAll("[\\(\\)]", "");
                print(express, countOperation);
            } else {
                recursion(express, countOperation);
            }
        } else if (expression.contains("(")) {
            int endBrackets = expression.indexOf(")");
            String var = expression.substring(0, endBrackets);
            int startBrackets = var.lastIndexOf("(") + 1;
            String beforeBrackets = expression.substring(0, startBrackets - 1);
            String bracketedExp = expression.substring(startBrackets, endBrackets);
            String afterBrackets = expression.substring(endBrackets + 1);
            bracketedExp = getCalculatedExpression(bracketedExp);
            express = beforeBrackets + bracketedExp + afterBrackets;
            recursion(express.replace("--", "+"), countOperation);
        } else if (isNumeric(expression)) {
            print(expression, countOperation);
        } else {
            print(getCalculatedExpression(expression), countOperation);
        }
    }

    private void print(String expression, int countOperation) {
        double d = Double.parseDouble(expression);
        String bd = BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP).toString();
        bd = new DecimalFormat("#.##").format(Double.valueOf(bd));
        System.out.println(bd + " " + countOperation);
    }

    private static String getCalculatedExpression(String bracketedExp) {
        while (bracketedExp.contains("sin")) {
            bracketedExp = sin(bracketedExp);
        }
        while (bracketedExp.contains("cos")) {
            bracketedExp = cos(bracketedExp);
        }
        while (bracketedExp.contains("tan")) {
            bracketedExp = tan(bracketedExp);
        }
        while (bracketedExp.contains("^")) {
            bracketedExp = pow(bracketedExp);
        }
        while (bracketedExp.contains("/")) {
            bracketedExp = div(bracketedExp).replace("--", "+");
        }
        while (bracketedExp.contains("*")) {
            bracketedExp = mult(bracketedExp).replace("--", "+");
        }
        while (bracketedExp.contains("-") || bracketedExp.contains("+")) {
            if (isNumeric(bracketedExp)) break;
            bracketedExp = plusminus(bracketedExp);
        }
        return bracketedExp;
    }

    public static String pow(String expression) {
        String[] s = expression.split("\\^", 2);
        String arg1 = getArgs(s[0], s[1])[0];
        String arg2 = getArgs(s[0], s[1])[1];
        double a1 = Double.parseDouble(arg1);
        double a2 = Double.parseDouble(arg2);
        return expression.replaceAll(arg1 + "\\^" + arg2, Math.pow(a1, a2) + "");
    }

    public static String mult(String expression) {
        String[] s = expression.split("\\*", 2);
        String arg1 = getArgs(s[0], s[1])[0];
        String arg2 = getArgs(s[0], s[1])[1];
        double a1 = Double.parseDouble(arg1);
        double a2 = Double.parseDouble(arg2);
        return expression.replaceAll(arg1 + "\\*" + arg2, a1 * a2 + "");
    }

    public static String div(String expression) {
        String[] s = expression.split("/", 2);
        String arg1 = getArgs(s[0], s[1])[0];
        String arg2 = getArgs(s[0], s[1])[1];
        double a1 = Double.parseDouble(arg1);
        double a2 = Double.parseDouble(arg2);
        return expression.replaceAll(arg1 + "/" + arg2, a1 / a2 + "");
    }

    public static String plusminus(String expression) {
        Pattern pattern1 = Pattern.compile("-?\\d+\\.?\\d*");
        Matcher matcher1 = pattern1.matcher(expression);
        double summ = 0;
        while (matcher1.find()) {
            summ += Double.parseDouble(matcher1.group());
        }
        return summ + "";
    }

    public static String sin(String expression) {
        String[] s = expression.split("sin", 2);
        String arg = getArgs(s[0], s[1])[1];
        double a = Double.parseDouble(arg);
        return expression.replaceAll("sin" + arg, Math.sin(Math.toRadians(a)) + "");
    }

    public static String cos(String expression) {
        String[] s = expression.split("cos", 2);
        String arg = getArgs(s[0], s[1])[1];
        double a = Double.parseDouble(arg);
        return expression.replaceAll("cos" + arg, Math.cos(Math.toRadians(a)) + "");
    }

    public static String tan(String expression) {
        String[] s = expression.split("tan", 2);
        String arg = getArgs(s[0], s[1])[1];
        double a = Double.parseDouble(arg);
        return expression.replaceAll("tan" + arg, Math.tan(Math.toRadians(a)) + "");
    }

    public static String[] getArgs(String arg1, String arg2) {
        String[] result = new String[2];
        Pattern pattern1 = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher1 = pattern1.matcher(arg1);
        while (matcher1.find()) {
            arg1 = matcher1.group();
        }
        Pattern pattern2 = Pattern.compile("-?\\d+\\.?\\d*");
        Matcher matcher2 = pattern2.matcher(arg2);
        while (matcher2.find()) {
            arg2 = matcher2.group();
            break;
        }
        result[0] = arg1;
        result[1] = arg2;
        return result;
    }

    public static boolean isNumeric(String expression) {
        try {
            Double.parseDouble(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int countOperations(String expression){
        int sin = expression.split("sin").length-1;
        int cos = expression.split("cos").length-1;
        int tan = expression.split("tan").length-1;
        int pow = expression.split("\\^").length-1;
        int plus = expression.split("\\+").length-1;
        int minus = expression.split("-").length-1;
        int mult = expression.split("\\*").length-1;
        int div = expression.split("/").length-1;
        return sin + cos + tan + pow + plus + minus + mult + div;
    }
}
