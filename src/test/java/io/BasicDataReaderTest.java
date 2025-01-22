/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author egorm
 */
class BasicDataReaderTest {

    private Path tempFilePath; // Используем Path для работы с временными файлами

    @BeforeEach
    public void setUp() throws IOException {
        // Создаём временный файл в стандартной временной директории
        tempFilePath = Files.createTempFile("testRead", ".tmp");
        try (FileWriter writer = new FileWriter(tempFilePath.toFile())) {
            System.out.println(tempFilePath.toString());
            writer.write("Test data for BasicDataReader");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Удаляем временный файл после завершения теста
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void testRead() throws IOException {
        BasicDataReader reader = new BasicDataReader();
        String result = reader.read(tempFilePath.getParent().toString(), null, tempFilePath.getFileName().toString()); // Передаём путь к временно созданному файлу
        assertEquals("Test data for BasicDataReader", result, "Reader did not return the expected content.");
    }
}