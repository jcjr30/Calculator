package com.jcjr30.calculatorvtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalcApplication extends Application {

    public static String initLayout;

    private static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("/com/jcjr30/calculatorvtwo" + initLayout));
        Scene scene;

        if (initLayout.equals("/fxml/scientific-layout.fxml"))  {
            scene = new Scene(fxmlLoader.load(), 375, 525);
        }
        else {
            scene = new Scene(fxmlLoader.load(), 300, 525);
        }
            loadFontIfPresent("fonts/dsdigital.TTF", 64);
            loadFontIfPresent("fonts/SourceSansPro-Semibold.otf", 64);
            loadFontIfPresent("fonts/SourceSansPro-Light.otf", 64);



        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();

        try {
            stage.getIcons().add(new Image(CalcApplication.class.getResourceAsStream("img/CalculatorV2-icon.png")));
        } catch (NullPointerException e) {
            System.out.println("Icon not found");
        }
    }

    public static void setInitLayout(String layout) {
        initLayout = layout;
    }
    public static void loadFontIfPresent(String fontPath, int size)  {
        try {
            if (Font.loadFont(CalcApplication.class.getResource(fontPath).toExternalForm(), size) != null) {
                System.out.println("Font loaded: " + fontPath);
            } else {
                System.out.println("Font not found: " + fontPath);
            }
        } catch (NullPointerException e) {
            System.out.println("Resource not found: " + fontPath);
        }
    }
}