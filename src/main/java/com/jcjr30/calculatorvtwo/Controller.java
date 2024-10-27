package com.jcjr30.calculatorvtwo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.util.Objects;

public class Controller {

    //Display Text
    @FXML
    private Text calculationText;

    private BigDecimal operator;
    private BigDecimal operand;
    private String operation;
    private boolean resetNextInput = false;


    private boolean equalsRepeat = false;
    private BigDecimal equalsBuffer;


    //Gets the text contained in the button that was clicked
    @FXML
    private void handleButtonClick(MouseEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        //Determines whether the text is numerical or an operation
        if (buttonText.matches("\\d")) {
            BigDecimal number = BigDecimal.valueOf(Double.parseDouble(button.getText()));
            appendNumber(number);
            equalsRepeat = false;
        }
        else {
            handleOperation(buttonText);
        }
    }

    //Displays numbers as they are input
    private void appendNumber(BigDecimal number) {
        String inputAppend = calculationText.getText();
        if (inputAppend.equals("--") || inputAppend.equals("+") || inputAppend.equals("-") || inputAppend.equals("*") || inputAppend.equals("/") || resetNextInput)   {
            inputAppend = "";
            resetNextInput = false;
        }
        inputAppend += number.stripTrailingZeros();
        calculationText.setText(inputAppend);
    }

    //Determines the operation input and handles accordingly
    private void handleOperation(String buttonText) {
        switch (buttonText) {

            case "C" -> {clear();}

            case "+", "-", "*", "/" -> {
                operator = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                calculationText.setText(buttonText);
                operation = buttonText;
            }

            //The first time '=' is pressed it acts as expected, but further presses take the original
            //operand and operate it on the result of the previous operation
            case "=" -> {

                if (equalsRepeat == false)  {
                    operand = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                    equalsBuffer = operand;
                    displayValue(Objects.requireNonNull(Operation.solveFunction(operator, operand, operation)).stripTrailingZeros().toPlainString());
                }
                else {
                    operator = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                    displayValue((Objects.requireNonNull(Operation.solveFunction(operator, equalsBuffer, operation))).stripTrailingZeros().toPlainString());
                }
                resetNextInput = true;
                equalsRepeat = true;
            }

            case "%" -> {
                if (calculationText.getText().equals("--") || calculationText.getText().equals("+") || calculationText.getText().equals("-") || calculationText.getText().equals("*") || calculationText.getText().equals("/"))   {
                    clear();
                }
                else {
                    BigDecimal inputAppend = BigDecimal.valueOf(Double.parseDouble((calculationText.getText()))).stripTrailingZeros();
                    inputAppend = inputAppend.multiply(BigDecimal.valueOf(0.01).stripTrailingZeros());
                    calculationText.setText(inputAppend.toString());
                }
            }

            case "<-" -> {
                String inputAppend = calculationText.getText();
                if (inputAppend.substring(inputAppend.length() - 1).matches("\\d"))  {
                    calculationText.setText(inputAppend.substring(0, inputAppend.length() - 1));
                }
                else {clear();}
            }

            case "." -> {
                String inputAppend = calculationText.getText();
                if (!inputAppend.contains(".")) {
                    inputAppend += ".";
                    calculationText.setText(inputAppend);
                }
            }
        }
    }

    //Method to clear
    private void clear() {
        calculationText.setText("--");
        operator = BigDecimal.valueOf(0);
        operand = BigDecimal.valueOf(0);
    }

    //Method to easily display numbers
    private void displayValue(BigDecimal value)  {
        calculationText.setText(String.valueOf(value));
    }
    private void displayValue(String value)  {
        calculationText.setText(value);
    }

}

