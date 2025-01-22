/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.actions;

import io.ArchiveDataReader;
import io.BasicDataReader;
import io.DataReader;
import io.EncryptedDataReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author egorm
 */
public class ReadCommand implements ActionCommand {
    private DataReader reader;
    private final String directoryPath;
    private final String archiveName;
    private final String fileName;
    private final String encryptorKey;
    private final JTextArea rawInputField; // Поле ввода для сырых данных

    public ReadCommand( JTextArea rawInputField, String directoryPath, String archiveName, String fileName, String encryptorKey) {
        this.reader = new BasicDataReader();
        this.directoryPath = directoryPath;
        this.archiveName = archiveName;
        this.fileName = fileName;
        this.encryptorKey = encryptorKey;
        this.rawInputField = rawInputField;
    }

    @Override
    public void execute() {
        String rawData;
        
        // Если есть архив
        if (archiveName != null) {
            reader = new ArchiveDataReader(reader);
        }

        // Если есть ключ шифрования
        if (encryptorKey != null) {
            reader = new EncryptedDataReader(reader, encryptorKey);
        }
        try {
            rawData = reader.read(directoryPath, archiveName, fileName);
            rawInputField.setText(rawData); 
        } catch (IOException ex) {
            Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}

