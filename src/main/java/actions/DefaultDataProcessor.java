/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

import java.util.Vector;
import main.app.Serializer;
import main.app.SerializerFactory;

/**
 *
 * @author egorm
 */
public class DefaultDataProcessor implements DataProcessor {
    private final SerializerFactory serializerFactory;
    private final String fileExtension;

    public DefaultDataProcessor(SerializerFactory serializerFactory, String fileExtension) {
        this.serializerFactory = serializerFactory;
        this.fileExtension = fileExtension;
    }

    @Override
    public Vector<String> deserialize(String rawData) {
        Serializer serializer = serializerFactory.creatSerializer(fileExtension);
        return serializer.deserialize(rawData);
    }

    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results) {
        Serializer serializer = serializerFactory.creatSerializer(fileExtension);
        return serializer.serialize(expressions, results);
    }
}