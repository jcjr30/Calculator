package com.jcjr30.calculatorvtwo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    @FXML
    private GridPane gridPane;
    @FXML
    private ChoiceBox<String> choiceSkin;
    @FXML
    private ChoiceBox<String> choiceCalcType;

    private static final String DEFAULT_THEME = "/com/jcjr30/calculatorvtwo/default.css";
    private static final String LIGHT_THEME = "/com/jcjr30/calculatorvtwo/light.css";


    @FXML
    public void initialize() {
        choiceCalcType.getItems().addAll("Basic", "Advanced", "Scientific");
        choiceCalcType.setValue("Basic");
        choiceSkin.getItems().addAll("Default", "Dark", "Light");
        choiceSkin.setValue("Default");

        switchToDefaultTheme();

        choiceSkin.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if (newValue.equals("Light")) {
               System.out.println(newValue);
                switchToLightTheme();
           }
            if (newValue.equals("Default")) {
                System.out.println(newValue);
                switchToDefaultTheme();
            }

        });
    }


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
        } else {
            handleOperation(buttonText);
        }
    }

    //Displays numbers as they are input
    private void appendNumber(BigDecimal number) {
        String inputAppend = calculationText.getText();
        if (inputAppend.equals("--") || inputAppend.equals("+") || inputAppend.equals("-") || inputAppend.equals("*") || inputAppend.equals("/") || resetNextInput) {
            inputAppend = "";
            resetNextInput = false;
        }
        inputAppend += number.stripTrailingZeros();
        calculationText.setText(inputAppend);
    }

    //Determines the operation input and handles accordingly
    private void handleOperation(String buttonText) {
        switch (buttonText) {

            case "C" -> {
                clear();
            }

            case "+", "-", "*", "/" -> {
                operator = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                calculationText.setText(buttonText);
                operation = buttonText;
            }

            //The first time '=' is pressed it acts as expected, but further presses take the original
            //operand and operate it on the result of the previous operation
            case "=" -> {

                if (equalsRepeat == false) {
                    operand = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                    equalsBuffer = operand;
                    displayValue(Objects.requireNonNull(Operation.solveFunction(operator, operand, operation)).stripTrailingZeros().toPlainString());
                } else {
                    operator = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                    displayValue((Objects.requireNonNull(Operation.solveFunction(operator, equalsBuffer, operation))).stripTrailingZeros().toPlainString());
                }
                resetNextInput = true;
                equalsRepeat = true;
            }

            case "%" -> {
                if (calculationText.getText().equals("--") || calculationText.getText().equals("+") || calculationText.getText().equals("-") || calculationText.getText().equals("*") || calculationText.getText().equals("/")) {
                    clear();
                } else {
                    BigDecimal inputAppend = BigDecimal.valueOf(Double.parseDouble((calculationText.getText()))).stripTrailingZeros();
                    inputAppend = inputAppend.multiply(BigDecimal.valueOf(0.01).stripTrailingZeros());
                    calculationText.setText(inputAppend.toString());
                }
            }

            case "<-" -> {
                String inputAppend = calculationText.getText();
                if (inputAppend.substring(inputAppend.length() - 1).matches("\\d")) {
                    calculationText.setText(inputAppend.substring(0, inputAppend.length() - 1));
                } else {
                    clear();
                }
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
    private void displayValue(BigDecimal value) {
        calculationText.setText(String.valueOf(value));
    }

    private void displayValue(String value) {
        calculationText.setText(value);
    }


    //Theme related methods

    public void switchToLightTheme() {
        switchTheme(LIGHT_THEME);
    }
    public void switchToDefaultTheme()  {
        switchTheme(DEFAULT_THEME);
    }

    private void switchTheme(String THEME) {
        if (gridPane.getScene() != null) {
            removeStylesheets();
            addStylesheet(THEME);

            System.out.println(THEME);

            gridPane.sceneProperty().get().getStylesheets().add(getClass().getResource(THEME).toExternalForm());
        } else {
            gridPane.sceneProperty().addListener((_, _, newScene) -> {
                if (newScene != null) {
                    newScene.getStylesheets().clear();
                    addStylesheet(THEME);
                }
            });
        }
    }
    public void addStylesheet(String stylesheetPath) {
        gridPane.getScene().getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
    }
    public void removeStylesheets() {
        gridPane.getScene().getStylesheets().clear();
    }
}

