/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author egorm
 */
public class BasicDataWriter implements DataWriter {
    @Override
    public void write(String directoryPath, String archiveName, String fileName, String data) throws IOException {
        Path filePath = Paths.get(directoryPath, fileName);
        Files.writeString(filePath, data);
    }
}
