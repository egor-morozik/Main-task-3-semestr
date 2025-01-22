/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.actions;

import javax.swing.JTextArea;
import utils.Calculator;
import settings.FileSettings;
import serializer.SerializerFactory;

/**
 *
 * @author egorm
 */
public class ActionController {
    private final ActionCommand readCommand;
    private final ActionCommand analyzeCommand;
    private final ActionCommand writeCommand;
    
    
    public ActionController(FileSettings fileSettings, JTextArea inputField, JTextArea outputField) 
    {
        this.readCommand = new ReadCommand(inputField, fileSettings.getInputDirectoryPath(), fileSettings.getInputFileArchive(), fileSettings.getInputFileName(), fileSettings.getInputFileKey());
        this.analyzeCommand = new AnalyzeCommand(inputField, outputField, fileSettings.getInputFileType(), fileSettings.getOutputFileType(), new SerializerFactory(), new Calculator());
        this.writeCommand = new WriteCommand(fileSettings.getOutputDirectoryPath(), fileSettings.getOutputFileArchive(), fileSettings.getOutputFileName(), outputField, fileSettings.getOutputFileKey());
    }
    
    public void onReadButtonClick() {
        readCommand.execute();
    }

    public void onAnalyzeButtonClick() {
        analyzeCommand.execute();
    }

    public void onWriteButtonClick() {
        writeCommand.execute();
    }
}

