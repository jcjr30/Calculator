package com.jcjr30.calculatorvtwo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class Controller {
    private BigDecimal operator;
    private BigDecimal operand;
    private String operation;
    private boolean resetNextInput = false;

    private boolean equalsRepeat = false;
    private BigDecimal equalsBuffer;

    //Display Text
    @FXML
    private Text calculationText;
    @FXML
    private GridPane gridPane;
    @FXML
    private ChoiceBox<String> choiceSkin;
    @FXML
    private ChoiceBox<String> choiceCalcType;

    //Operation Buttons
    @FXML
    Button equalsButton;
    @FXML
    Button clearButton;
    @FXML
    Button backButton;
    @FXML
    Button percentButton;
    @FXML
    Button divideButton;
    @FXML
    Button multiplyButton;
    @FXML
    Button minusButton;
    @FXML
    Button addButton;
    @FXML
    Button decimalButton;

    //Integer Buttons
    @FXML
    Button zeroButton;
    @FXML
    Button oneButton;
    @FXML
    Button twoButton;
    @FXML
    Button threeButton;
    @FXML
    Button fourButton;
    @FXML
    Button fiveButton;
    @FXML
    Button sixButton;
    @FXML
    Button sevenButton;
    @FXML
    Button eightButton;
    @FXML
    Button nineButton;

    //Scientific Buttons
    @FXML
    Button radicalButton;
    @FXML
    Button powerXButton;
    @FXML
    Button negativeButton;
    @FXML
    Button naturalLogButton;
    @FXML
    Button logButton;



    private static final String DEFAULT_THEME = "/com/jcjr30/calculatorvtwo/css/default.css";
    private static final String LIGHT_THEME = "/com/jcjr30/calculatorvtwo/css/light.css";
    private static final String DARK_THEME = "/com/jcjr30/calculatorvtwo/css/dark.css";
    private static final String BASIC_LAYOUT = "fxml/basic-layout.fxml";
    private static final String SCIENTIFIC_LAYOUT = "fxml/scientific-layout.fxml";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File themeFile = new File("data/theme.json");
    private final File layoutFile = new File("data/calcType.json");

    private boolean initializing = false;
    private int count = 0;

    //Initialize ChoiceBox's and Theme
    @FXML
    public void initialize() {
        if (initializing) return;
        initializing = true;

        ReadWrite.createFolder();

        if (!layoutFile.exists()) {ReadWrite.writeJson(layoutFile, "/fxml/basic-layout.fxml");}

        choiceSkin.getItems().addAll("Light", "Default", "Dark");

        //Switch to previously stored theme
        try {
            if (themeFile.exists()) {
                JsonNode nodeTheme = objectMapper.readTree(themeFile);
                String initTheme = nodeTheme.asText();
                switchTheme(initTheme);

                switch (initTheme) {
                    case DEFAULT_THEME -> choiceSkin.setValue("Default");
                    case LIGHT_THEME -> choiceSkin.setValue("Light");
                    case DARK_THEME -> choiceSkin.setValue("Dark");
                }
            } else {
                switchTheme(DEFAULT_THEME);
                choiceSkin.setValue("Default");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Add listener to the 'skin' ChoiceBox
        choiceSkin.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if (newValue.equals("Light")) {
               System.out.println(newValue);
                switchTheme(LIGHT_THEME);
           }
            if (newValue.equals("Default")) {
                System.out.println(newValue);
                switchTheme(DEFAULT_THEME);
            }
            if (newValue.equals("Dark")) {
                System.out.println(newValue);
                switchTheme(DARK_THEME);
            }
        });

        choiceCalcType.getItems().addAll("Basic", "Scientific");
        choiceCalcType.setValue(ReadWrite.fxmlPathToLayout());
        choiceCalcType.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {

            try {
                ReadWrite.writeLayoutJson(newValue, layoutFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            if (oldValue != null) {
                if (newValue.equals("Basic") && !oldValue.equals("Basic")) {
                    CalcApplication.setInitLayout("/fxml/basic-layout.fxml");
                    loadLayout(BASIC_LAYOUT);
                } else if (newValue.equals("Scientific") && !oldValue.equals("Scientific")) {
                    CalcApplication.setInitLayout("/fxml/scientific-layout.fxml");
                    loadLayout(SCIENTIFIC_LAYOUT);
                }
            }
        });
        initializing = false;
    }

    private void loadLayout(String fxmlFile) {
        Stage stage;
        Parent root;

        try {

            stage = (Stage) gridPane.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(fxmlFile));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to easily display numbers
    private void displayValue(BigDecimal value) {
        calculationText.setText(value.setScale(7, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
    }
    private void displayValue(String value) {
        calculationText.setText(value);
    }

    //Gets the text contained in the button that was clicked
    @FXML
    private void handleButtonClick(Event event) {

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
    @FXML
    private void handleKeyPress(KeyEvent e) {
        KeyCode input = e.getCode();
        System.out.println(input.getName());

        if (e.isShiftDown() && ((e.getCode().equals(KeyCode.DIGIT5) || e.getCode().equals(KeyCode.NUMPAD5)) || (e.getCode().equals(KeyCode.MINUS) || e.getCode().equals(KeyCode.SUBTRACT))))   {
            switch (input) {
                case DIGIT5, NUMPAD5 -> percentButton.fire();
                case MINUS, SUBTRACT -> negativeButton.fire();
            }
        }   else {
            switch (input) {
                //Integer key inputs
                case DIGIT0, NUMPAD0 -> zeroButton.fire();
                case DIGIT1, NUMPAD1 -> oneButton.fire();
                case DIGIT2, NUMPAD2 -> twoButton.fire();
                case DIGIT3, NUMPAD3 -> threeButton.fire();
                case DIGIT4, NUMPAD4 -> fourButton.fire();
                case DIGIT5, NUMPAD5 -> fiveButton.fire();
                case DIGIT6, NUMPAD6 -> sixButton.fire();
                case DIGIT7, NUMPAD7 -> sevenButton.fire();
                case DIGIT8, NUMPAD8 -> eightButton.fire();
                case DIGIT9, NUMPAD9 -> nineButton.fire();

                //Operation key inputs
                case ADD, PLUS -> addButton.fire();
                case SUBTRACT, MINUS -> minusButton.fire();
                case DIVIDE, SLASH -> divideButton.fire();
                case MULTIPLY, ASTERISK -> multiplyButton.fire();

                //Scientific key inputs
                case N -> naturalLogButton.fire();
                case L -> logButton.fire();
                case X -> powerXButton.fire();

                //Other key inputs
                case ESCAPE -> clearButton.fire();
                case BACK_SPACE -> backButton.fire();
                case DECIMAL, PERIOD -> decimalButton.fire();
            }
        }
        
    }

    //Displays numbers as they are input
    private void appendNumber(BigDecimal number) {
        equalsButton.requestFocus();

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

            case "C" -> clear();
            case "+", "-", "*", "/" -> {
                operator = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                calculationText.setText(buttonText);
                operation = buttonText;
            }
            //The first time '=' is pressed it acts as expected, but further presses take the original
            //operand and operate it on the result of the previous operation
            case "=" -> {
                if (calculationText.getText().contains("^")) {
                    String [] expoParser = calculationText.getText().split("\\^");
                    operand = BigDecimal.valueOf(Double.parseDouble(expoParser[1]));
                    displayValue(Objects.requireNonNull(Operation.solveFunction(operator, operand, operation)));
                    break;
                }
                if (!equalsRepeat) {
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
                    displayValue(inputAppend);
                }
            }

            case "(-)" -> {
                String inputAppend = calculationText.getText();
                if (!inputAppend.equals("--") && !inputAppend.equals("+") && !inputAppend.equals("-") && !inputAppend.equals("*") && !inputAppend.equals("/")) {
                    BigDecimal negOp = BigDecimal.valueOf((Double.parseDouble(calculationText.getText()) * -1));
                    displayValue(negOp);
                }
            }

            case "<-" -> {
                String inputAppend = calculationText.getText();
                if (inputAppend.substring(inputAppend.length() - 1).matches("[\\d.]")) {
                    calculationText.setText(inputAppend.substring(0, inputAppend.length() - 1));
                } else {
                    clear();
                }
            }

            case "." -> {
                String inputAppend = calculationText.getText();

                //Checks if input is not a number and does not already contain a '.'
                //if true, set Text to "0."
                if (inputAppend.equals("--") || inputAppend.equals("+") || inputAppend.equals("-") || inputAppend.equals("*") || inputAppend.equals("/")) {
                    calculationText.setText("0.");
                }
                //If input is a number and does not already contain a '.'
                //then append a '.' to the current Text
                else if (!inputAppend.contains(".")) {
                    inputAppend += ".";
                    calculationText.setText(inputAppend);
                    resetNextInput = false;
                }
            }
            //Scientific Inputs
            case "√" -> {
                String inputAppend = calculationText.getText();
                System.out.println(inputAppend);
                if (!inputAppend.equals("--") && !inputAppend.equals("+") && !inputAppend.equals("-") && !inputAppend.equals("*") && !inputAppend.equals("/")) {
                   BigDecimal pow = BigDecimal.valueOf(Double.parseDouble(calculationText.getText()));
                   String result = pow.sqrt(new MathContext(7)).stripTrailingZeros().toPlainString();
                   displayValue(result);
                }
            }
            case "^x" -> {
                String inputAppend = calculationText.getText();

                if (!inputAppend.equals("--") && !inputAppend.equals("+") && !inputAppend.equals("-") && !inputAppend.equals("*") && !inputAppend.contains("^")) {
                    calculationText.setText(inputAppend + "^");
                    operator = BigDecimal.valueOf(Double.parseDouble(inputAppend));
                    operation = "^x";
                    resetNextInput = false;
                }
            }
            case "log" -> {
                String inputAppend = calculationText.getText();

                if (!inputAppend.equals("--") && !inputAppend.equals("+") && !inputAppend.equals("-") && !inputAppend.equals("*") && !inputAppend.contains("^")) {
                    double doubleInputAppend = Math.log10(Double.parseDouble(calculationText.getText()));
                    displayValue(BigDecimal.valueOf(doubleInputAppend));
                    resetNextInput = false;
                }
            }
            case "ln" -> {
                String inputAppend = calculationText.getText();

                if (!inputAppend.equals("--") && !inputAppend.equals("+") && !inputAppend.equals("-") && !inputAppend.equals("*") && !inputAppend.contains("^")) {
                    double doubleInputAppend = Math.log(Double.parseDouble(calculationText.getText()));
                    displayValue(BigDecimal.valueOf(doubleInputAppend).stripTrailingZeros().toPlainString());
                    resetNextInput = false;
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

    //Theme related methods
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
        if (count > 0) {
            ReadWrite.writeJson(themeFile, THEME);
        }
        count++;
    }
    public void addStylesheet(String stylesheetPath) {
        gridPane.getScene().getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
    }
    public void removeStylesheets() {
        gridPane.getScene().getStylesheets().clear();
    }
}

