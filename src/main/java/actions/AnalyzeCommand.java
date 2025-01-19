/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

import java.util.Vector;
import javax.swing.JTextArea;
import main.app.Calculator;
import main.app.Serializer;
import main.app.SerializerFactory;

/**
 *
 * @author egorm
 */

public class AnalyzeCommand implements ActionCommand {

    private final JTextArea rawInputField;
    private final JTextArea processedOutputField;
    private final Calculator calculator;
    private final SerializerFactory serializerFactory;
    private final String fileExtension;
    
    public AnalyzeCommand(JTextArea rawInputField, JTextArea processedOutputField, String fileExtension, SerializerFactory serializerFactory, Calculator calculator ) {
        this.fileExtension = fileExtension;
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
        DataProcessor dataProcessor = new DefaultDataProcessor(serializerFactory, fileExtension);

        // Десериализация
        Vector<String> expressions = dataProcessor.deserialize(rawData);

        // Вычисление
        Vector<Double> results = calculator.evaluate(expressions);

        // Сериализация
        String outputData = dataProcessor.serialize(expressions, results);

        // Отправка результата
        processedOutputField.setText(outputData);
    }

}

