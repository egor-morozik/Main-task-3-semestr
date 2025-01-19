/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

import java.io.IOException;

/**
 *
 * @author egorm
 */
// Декоратор для записи данных с поддержкой архивирования

public interface DataWriter {
    void write(String directoryPath, String archiveName, String fileName, String data) throws IOException;
}
