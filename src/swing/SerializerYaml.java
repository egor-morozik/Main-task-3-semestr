/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.app;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author egorm
 */
public class SerializerYaml extends Serializer {
    
    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        Vector<Map<String, Object>> resultsList = new Vector<>();

        for (int i = 0; i < expressions.size(); i++) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("expression", expressions.get(i));
            resultMap.put("value", results.get(i));

            Map<String, Object> outerResultMap = new HashMap<>();
            outerResultMap.put("result", resultMap);

            resultsList.add(outerResultMap);
        }

        Map<String, Object> yamlData = new HashMap<>();
        yamlData.put("results", resultsList);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        return yaml.dump(yamlData); 
    }
    
    @Override
    public Vector<String> deserialize(String data)
    {
        Yaml yaml = new Yaml();
        
        // Загружаем данные из YAML-строки
        Map<String, Object> datas = yaml.load(data);
        
        //Vector<Map<String, Object>> expressions = (Vector<Map<String, Object>>) datas.get("expressions");
        List<Map<String, Object>> expressionsList = (List<Map<String, Object>>) datas.get("expressions");
        for (Map<String, Object> map : expressionsList) {
            System.out.println(map);
        }
        Vector<Map<String, Object>> expressions = new Vector<>(expressionsList);
        // Вектор для хранения выражений
        Vector<String> expressionList = new Vector<>();
        // Карта для хранения переменных
        Map<String, Integer> variablesMap = new HashMap<>();

        for (Map<String, Object> expressionEntry : expressions) {

            String value = (String) expressionEntry.get("value");
            Map<String, Integer> variables = (Map<String, Integer>) expressionEntry.get("variables");

            // Добавляем выражение в вектор
            expressionList.add(value);
            // Добавляем переменные в карту
            variablesMap.putAll(variables);
        }
        
        return substitution(expressionList, variablesMap);
    }
}
