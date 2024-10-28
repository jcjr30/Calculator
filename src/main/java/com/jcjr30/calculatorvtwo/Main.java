package com.jcjr30.calculatorvtwo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    static String initialFxml;

    public static void main(String[] args) throws IOException {

        final ObjectMapper mapper = new ObjectMapper();
        final File layoutFile = new File("calcType.json");

        if (layoutFile.exists()) {
            JsonNode node = mapper.readTree(layoutFile);
            String layout = node.asText();
            CalcApplication.setInitLayout(layout);
            initialFxml = layout;
        }
        else {
            initialFxml = "/fxml/basic-layout.fxml";
            CalcApplication.setInitLayout(initialFxml);
        }

        CalcApplication.launch(CalcApplication.class, args);
    }
}
