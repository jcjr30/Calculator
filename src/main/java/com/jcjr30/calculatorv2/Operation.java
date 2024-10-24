package com.jcjr30.calculatorv2;

public class Operation {

    public static double solveFunction(double a, double b, String operation)   {

        switch (operation) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return a / b;
            }
        }
        return 0;
        }
}
