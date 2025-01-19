/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sub;

import java.io.IOException;

/**
 *
 * @author egorm
 */
public interface Archiver {
    void compress(String directoryPath, String archiveName, String fileName, String data) throws IOException;
    String decompress(String directoryPath, String archiveName, String fileName) throws IOException;
}

