package com.jcjr30.calculatorvtwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFolder {
    private static final Path path = Paths.get("data");

    public static void createFolder() {
        try {
            Files.createDirectories(path);
            System.out.println("Folder created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
