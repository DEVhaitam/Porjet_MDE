package com.example;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    public String readFile(String filePath) {
        String content = "";
        try {
            // Read all lines from the file
            Path path = Paths.get(filePath);
            content = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
