/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author egorm
 */
public class BasicDataReader implements DataReader {
    @Override
    public String read(String directoryPath, String archiveName, String fileName) throws IOException {
        Path filePath = Paths.get(directoryPath, fileName);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }
        return Files.readString(filePath);
    }
}
