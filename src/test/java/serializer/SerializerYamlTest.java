/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package serializer;

import java.util.Vector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author egorm
 */
public class SerializerYamlTest {
    
@Test
    void testSerialize() {
        SerializerYaml serializer = new SerializerYaml();

        // Входные данные
        Vector<String> expressions = new Vector<>();
        expressions.add("x + y");
        expressions.add("a * b");

        Vector<Double> results = new Vector<>();
        results.add(5.0);
        results.add(10.0);

        // Выполняем сериализацию
        String yamlOutput = serializer.serialize(expressions, results);

        // Проверяем, что YAML содержит ожидаемые данные
        Yaml yaml = new Yaml();
        Map<String, Object> parsedOutput = yaml.load(yamlOutput);

        assertNotNull(parsedOutput);
        assertTrue(parsedOutput.containsKey("results"));

        List<Map<String, Object>> resultsList = (List<Map<String, Object>>) parsedOutput.get("results");
        assertEquals(2, resultsList.size());

        Map<String, Object> firstResult = (Map<String, Object>) resultsList.get(0).get("result");
        assertEquals("x + y", firstResult.get("expression"));
        assertEquals(5.0, firstResult.get("value"));
    }

    @Test
    void testDeserialize() {
        SerializerYaml serializer = new SerializerYaml();

        // YAML входные данные
        String yamlInput = """
        expressions:
          - value: "x + y"
            variables:
              x: 2
              y: 3
          - value: "a * b"
            variables:
              a: 4
              b: 2
        """;

        // Выполняем десериализацию
        Vector<String> result = serializer.deserialize(yamlInput);

        // Ожидаемые данные
        Vector<String> expected = new Vector<>();
        expected.add("2 + 3");
        expected.add("4 * 2");

        // Проверяем результат
        assertEquals(expected, result);
    }

    @Test
    void testSerializeAndDeserialize() {
        SerializerYaml serializer = new SerializerYaml();

        // Исходные данные
        Vector<String> expressions = new Vector<>();
        expressions.add("x + y");
        expressions.add("a * b");

        Vector<Double> results = new Vector<>();
        results.add(5.0);
        results.add(10.0);

        // Сериализация
        String yamlOutput = serializer.serialize(expressions, results);

        // Создаем входные данные для десериализации
        String yamlInput = """
        expressions:
          - value: "x + y"
            variables:
              x: 2
              y: 3
          - value: "a * b"
            variables:
              a: 4
              b: 2
        """;

        // Десериализация
        Vector<String> deserializedResult = serializer.deserialize(yamlInput);

        // Ожидаемые данные
        Vector<String> expected = new Vector<>();
        expected.add("2 + 3");
        expected.add("4 * 2");

        assertEquals(expected, deserializedResult);
    }
}
