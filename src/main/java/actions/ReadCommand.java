/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

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

    public ReadCommand(DataReader reader, String directoryPath, String archiveName, String fileName, JTextArea rawInputField) {
        this.reader = reader;
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
            rawInputField.setText(rawData); // Записываем данные в GUI
        } catch (IOException ex) {
            Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

