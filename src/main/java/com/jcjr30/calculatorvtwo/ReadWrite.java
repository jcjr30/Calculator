package com.jcjr30.calculatorvtwo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadWrite {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASIC_LAYOUT = "/fxml/basic-layout.fxml";
    private static final String SCIENTIFIC_LAYOUT = "/fxml/scientific-layout.fxml";

    public static void writeJson(File file, String json)  {
        try {
            objectMapper.writeValue(file, json);
            System.out.println("File saved to: " + file.getAbsolutePath());
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fxmlPathToLayout()  {

        if (CalcApplication.initLayout.equals(BASIC_LAYOUT)) {
            return "Basic";
        }
        if (CalcApplication.initLayout.equals(SCIENTIFIC_LAYOUT)) {
            return "Scientific";
        }
        return "error";
    }

    public static void writeLayoutJson(String currentLayout, File file) throws IOException {

        if (currentLayout.equals("Basic")) {
            writeJson(file, BASIC_LAYOUT);
        }
        if (currentLayout.equals("Scientific")) {
            writeJson(file, SCIENTIFIC_LAYOUT);
        }
    }
}
