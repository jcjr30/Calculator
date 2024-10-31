package com.jcjr30.calculatorvtwo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final File layoutFile = new File("data/calcType.json");

        if (layoutFile.exists()) {
            JsonNode node = mapper.readTree(layoutFile);
            String layout = node.asText();
            CalcApplication.setInitLayout(layout);
        } else {
            CalcApplication.setInitLayout("/fxml/basic-layout.fxml");
        }

        CalcApplication.launch(CalcApplication.class, args);
    }
}
