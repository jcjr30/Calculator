package com.jcjr30.calculatorvtwo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operation {

    public static BigDecimal solveFunction(BigDecimal a, BigDecimal b, String operation)   {

        switch (operation) {
            case "+" -> {
                return a.add(b);
            }
            case "-" -> {
                return a.subtract(b);
            }
            case "*" -> {
                return a.multiply(b);
            }
            case "/" -> {
                return a.divide(b, 7, RoundingMode.UP).stripTrailingZeros();
            }
        }
        return null;
        }
}
