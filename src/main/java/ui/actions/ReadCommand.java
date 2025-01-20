/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.actions;

import io.BasicDataReader;
import io.DataReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author egorm
 */
public class ReadCommand implements ActionCommand {
    private final DataReader reader;
    private final String directoryPath;
    private final String archiveName;
    private final String fileName;
    private final JTextArea rawInputField; // Поле ввода для сырых данных

    public ReadCommand( JTextArea rawInputField, String directoryPath, String archiveName, String fileName) {
        this.reader = new BasicDataReader();
        this.directoryPath = directoryPath;
        this.archiveName = archiveName;
        this.fileName = fileName;
        this.rawInputField = rawInputField;
    }

    @Override
    public void execute() {
        String rawData;
        try {
            rawData = reader.read(directoryPath, archiveName, fileName);
            rawInputField.setText(rawData); 
        } catch (IOException ex) {
            Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

