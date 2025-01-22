/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.actions;

import io.ArchiveDataWriter;
import io.BasicDataWriter;
import io.DataWriter;
import io.EncryptedDataWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author egorm
 */
public class WriteCommand implements ActionCommand {
    private DataWriter writer;
    private final String directoryPath;
    private final String archiveName;
    private final String fileName;
    private final String encryptorKey;
    private final JTextArea processedOutputField; // Поле ввода для сырых данных

    public WriteCommand(String directoryPath, String archiveName, String fileName, JTextArea processedOutputField, String encryptorKey) {
        this.writer = new BasicDataWriter();
        this.directoryPath = directoryPath;
        this.archiveName = archiveName;
        this.fileName = fileName;
        this.encryptorKey = encryptorKey;
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
        try {
            // Если необходимо архивирование
            if (archiveName != null) {
                writer = new ArchiveDataWriter(writer);
            }

            // Если требуется шифрование
            if (encryptorKey != null) {
                writer = new EncryptedDataWriter(writer, encryptorKey);
            }

            // Формируем полный путь
            String filePath = directoryPath + "/" + archiveName;

            // Получаем данные из области вывода
            String content = processedOutputField.getText();
            writer.write(directoryPath, archiveName, fileName, content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}