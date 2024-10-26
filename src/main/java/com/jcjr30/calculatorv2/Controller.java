package com.jcjr30.calculatorv2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    //Display Text
    @FXML
    private Text calculationText;

    private double firstInput;
    private double secondInput;
    private String operator;
    private boolean resetNextInput = false;


    private boolean equalsRepeat = false;
    private double equalsBuffer;

    @FXML
    private void handleButtonClick(MouseEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.matches("\\d")) {
            double number = Double.parseDouble(button.getText());
            appendNumber(number);
            equalsRepeat = false;
        }
        else {
            handleOperation(buttonText);
        }
    }


    private void appendNumber(double number) {
        String inputAppend = calculationText.getText();
        if (inputAppend.equals("--") || inputAppend.equals("+") || inputAppend.equals("-") || inputAppend.equals("*") || inputAppend.equals("/") || resetNextInput)   {
            inputAppend = "";
            resetNextInput = false;
        }
        inputAppend += (int) number;
        calculationText.setText(inputAppend);
    }

    private void handleOperation(String buttonText) {
        switch (buttonText) {
            case "C" -> {
                calculationText.setText("--");
                firstInput = 0;
                secondInput = 0;

            }
            case "+", "-", "*", "/" -> {
                firstInput = Double.parseDouble(calculationText.getText());
                calculationText.setText(buttonText);
                operator = buttonText;

            }
            case "=" -> {

                if (equalsRepeat == false)  {
                    secondInput = Double.parseDouble(calculationText.getText());
                    equalsBuffer = secondInput;
                    calculationText.setText(String.valueOf(Operation.solveFunction(firstInput, secondInput, operator)));
                }
                else {
                    firstInput = Double.parseDouble(calculationText.getText());
                    calculationText.setText(String.valueOf(Operation.solveFunction(firstInput, equalsBuffer, operator)));
                    resetNextInput = true;
                }
                equalsRepeat = true;
            }
            case "%" -> {
                String inputAppend = "0." + calculationText.getText();
                calculationText.setText(inputAppend);
            }
        }
    }

}