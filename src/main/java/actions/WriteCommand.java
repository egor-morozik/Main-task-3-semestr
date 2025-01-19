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
public class WriteCommand implements ActionCommand {
    private final DataWriter writer;
    private final String directoryPath;
    private final String archiveName;
    private final String fileName;
    private final JTextArea processedOutputField; // Поле ввода для сырых данных

    public WriteCommand(DataWriter writer, String directoryPath, String archiveName, String fileName, JTextArea processedOutputField) {
        this.writer = writer;
        this.directoryPath = directoryPath;
        this.archiveName = archiveName;
        this.fileName = fileName;
        this.processedOutputField = processedOutputField;
    }

    @Override
    public void execute() {
        String processedData = processedOutputField.getText();
        if (processedData.isEmpty()) {
            throw new IllegalStateException("Поле ввода для обработанных данных пусто.");
        }
        try {
            writer.write(directoryPath, archiveName, fileName, processedData);
        } catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}