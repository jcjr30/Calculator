package com.jcjr30.calculatorv2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    //Display Text
    @FXML
    private Text calculationText;

    //Number Buttons
    @FXML
    private Button zeroButton;
    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button threeButton;
    @FXML
    private Button fourButton;
    @FXML
    private Button fiveButton;
    @FXML
    private Button sixButton;
    @FXML
    private Button sevenButton;
    @FXML
    private Button eightButton;
    @FXML
    private Button nineButton;


    //Operation Buttons
    @FXML
    private Button equalsButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;
    @FXML
    private Button decimalButton;
    @FXML
    private Button percentageButton;
    @FXML
    private Button minusButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button divideButton;
    @FXML
    private Button addButton;

    private double firstInput;
    private double secondInput;
    private String operator;
    private int step;

    @FXML
    private void handleButtonClick(MouseEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.matches("\\d")) {
            double number = Double.parseDouble(button.getText());
            appendNumber(number);
        }
        else {
            handleOperation(buttonText);
        }
    }


    private void appendNumber(double number) {
        String inputAppend = calculationText.getText();
        if (inputAppend.equals("--"))   {
            inputAppend = "";
        }
        inputAppend += (int) number;
        calculationText.setText(inputAppend);
    }

    private void handleOperation(String buttonText) {
        if (buttonText.equals("C")) {
            calculationText.setText("--");
            firstInput = 0;
            secondInput = 0;
            step = 1;
        }
        else if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
            firstInput = Double.parseDouble(calculationText.getText());
            calculationText.setText("--");
            operator = buttonText;
            step = 2;
        }
        else if (buttonText.equals("=")) {
            secondInput = Double.parseDouble(calculationText.getText());
            calculationText.setText(String.valueOf(Operation.solveFunction(firstInput, secondInput, operator)));
            step = 1;
        }
        else if (buttonText.equals("%"))  {
            String inputAppend = "0." + calculationText.getText();
            calculationText.setText(inputAppend);
        }
    }

}