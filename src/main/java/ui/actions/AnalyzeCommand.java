/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.actions;

import data.DataProcessor;
import data.DefaultDataProcessor;
import java.util.Vector;
import javax.swing.JTextArea;
import utils.Calculator;
import serializer.SerializerFactory;

/**
 *
 * @author egorm
 */

public class AnalyzeCommand implements ActionCommand {

    private final JTextArea rawInputField;
    private final JTextArea processedOutputField;
    private final Calculator calculator;
    private final SerializerFactory serializerFactory;
    private final String inputFileExtension;
    private final String outputFileExtension;
    
    public AnalyzeCommand(JTextArea rawInputField, JTextArea processedOutputField, String inputFileExtension, String outputFileExtension, SerializerFactory serializerFactory, Calculator calculator ) {
        this.inputFileExtension = inputFileExtension;
        this.outputFileExtension = outputFileExtension;
        this.serializerFactory = serializerFactory;
        this.calculator = calculator;
        this.rawInputField = rawInputField;
        this.processedOutputField = processedOutputField;
    }

    @Override
    public void execute() {
        // Получаем сырые данные
        String rawData = rawInputField.getText();

        // Создаём DataProcessor
        DataProcessor dataDeserializeProcessor = new DefaultDataProcessor(serializerFactory, inputFileExtension);
        
        DataProcessor dataSerializeProcessor = new DefaultDataProcessor(serializerFactory, outputFileExtension);

        // Десериализация
        Vector<String> expressions = dataDeserializeProcessor.deserialize(rawData);

        // Вычисление
        Vector<Double> results = calculator.evaluate(expressions);

        // Сериализация
        String outputData = dataSerializeProcessor.serialize(expressions, results);

        // Отправка результата
        processedOutputField.setText(outputData);
    }

}

