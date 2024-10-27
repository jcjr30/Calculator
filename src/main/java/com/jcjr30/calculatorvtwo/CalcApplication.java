package com.jcjr30.calculatorvtwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("fxml/basic-layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 525);


        //scene.getStylesheets().add((getClass().getResource("default.css")).toExternalForm());

        Font.loadFont(getClass().getResource("fonts/dsdigital.TTF").toExternalForm(), 16);
        Font.loadFont(getClass().getResource("fonts/SourceSansPro-Semibold.otf").toExternalForm(), 16);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}