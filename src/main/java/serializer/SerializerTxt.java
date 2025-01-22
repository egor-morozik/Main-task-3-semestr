/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author egorm
 */
public class SerializerTxt extends Serializer {
    
    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        String result = "";
        for (int i=0; i < expressions.size(); i++)
        {
            result += expressions.get(i) + " = " + Double.toString(results.get(i)) + "\n";
            System.out.println("res" + result + "\n");
        }
        return result;
    }
    
    @Override
    public Vector<String> deserialize(String data)
    {
        Vector<String> expressions = new Vector<>();
        Map<String, Integer> values = new HashMap<>();

        // Разбиваем данные по строкам
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Убираем лишние пробелы и символы новой строки
            line = line.trim();

            // Пропускаем пустые строки
            if (line.isEmpty()) {
                continue;
            }

            // Проверяем, содержит ли строка знак "="
            if (line.contains("=")) {
                try {
                    // Разбиваем строку на ключ и значение
                    String key = line.substring(0, line.indexOf("=")).trim();
                    String valueStr = line.substring(line.indexOf("=") + 1).trim();

                    // Преобразуем значение в число и сохраняем в Map
                    int value = Integer.parseInt(valueStr);
                    values.put(key, value);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Невозможно преобразовать значение в число: " + line);
                }
            } else {
                // Добавляем строку в список выражений
                expressions.add(line);
            }
        }

        // Вызываем метод для замены переменных в выражениях
        return substitution(expressions, values);
    }
}
