/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.IOException;

/**
 *
 * @author egorm
 */
public interface DataReader {
    String read(String directoryPath, String archiveName, String fileName) throws IOException;
}
