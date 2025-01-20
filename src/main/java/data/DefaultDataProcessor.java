/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.Vector;
import serializer.Serializer;
import serializer.SerializerFactory;

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