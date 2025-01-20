/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.Vector;

/**
 *
 * @author egorm
 */
public interface DataProcessor {
    Vector<String> deserialize(String rawData);
    String serialize(Vector<String> expressions, Vector<Double> results);
}
