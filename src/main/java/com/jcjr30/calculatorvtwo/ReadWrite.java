package com.jcjr30.calculatorvtwo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWrite {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASIC_LAYOUT = "/fxml/basic-layout.fxml";
    private static final String SCIENTIFIC_LAYOUT = "/fxml/scientific-layout.fxml";

    private static final Path path = Paths.get("data");

    public static void writeJson(File file, String json)  {
        try {
            objectMapper.writeValue(file, json);
            System.out.println("File saved to: " + file.getAbsolutePath());
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fxmlPathToChoiceBoxItem()  {

        if (CalcApplication.initLayout.equals(BASIC_LAYOUT)) {
            return "Basic";
        }
        if (CalcApplication.initLayout.equals(SCIENTIFIC_LAYOUT)) {
            return "Scientific";
        }
        return "error";
    }

    public static void writeLayoutJsonFromChoiceBoxItem(String layout, File file) throws IOException {

        if (layout.equals("Basic")) {
            writeJson(file, BASIC_LAYOUT);
        }
        if (layout.equals("Scientific")) {
            writeJson(file, SCIENTIFIC_LAYOUT);
        }
    }

    public static void createFolder() {
        try {
            Files.createDirectories(path);
            System.out.println("Folder created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
