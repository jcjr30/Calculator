package com.jcjr30.calculatorvtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

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
        Font.loadFont(getClass().getResource("fonts/dsdigital.TTF").toExternalForm(), 16);
        Font.loadFont(getClass().getResource("fonts/SourceSansPro-Semibold.otf").toExternalForm(), 16);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void setInitLayout(String layout) {
        initLayout = layout;
    }
}