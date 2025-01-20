/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 *
 * @author egorm
 */
public class SerializerHtml extends Serializer {
    
    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        StringBuilder htmlBuilder = new StringBuilder();

        // Начало HTML структуры
        htmlBuilder.append("<results>\n");

        // Проверка на совпадение размеров массивов
        if (expressions.size() != results.size()) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }

        // Генерация содержимого
        for (int i = 0; i < expressions.size(); i++) {
            htmlBuilder.append("    <result>\n");
            htmlBuilder.append("        <expression>").append(expressions.get(i)).append("</expression>\n");
            htmlBuilder.append("        <value>").append(results.get(i)).append("</value>\n");
            htmlBuilder.append("    </result>\n");
        }

        // Конец HTML структуры
        htmlBuilder.append("</results>");

        return htmlBuilder.toString();
    }
    
    @Override
    public Vector<String> deserialize(String data)
    {
        Vector <String> expressions = new Vector <String>();
	Map<String, Integer> values = new HashMap<String, Integer>();
        // Парсим HTML-контент
        Document document = (Document) Jsoup.parse(data);

        // Извлекаем все элементы <expression>
        Elements elemexpressions = (Elements) document.select("expression");

        for (Element expression : elemexpressions) {
            // Извлекаем значение выражения
            String value = expression.select("value").text();
            expressions.add(value);

            // Извлекаем переменные и их значения
            Elements variables = (Elements) expression.children();
            for (Element variable : variables) {
                if (!variable.tagName().equals("value")) {
                    String varName = variable.tagName();
                    int varValue = Integer.parseInt(variable.text());
                    values.put(varName, varValue);
                }
            }
        }
        return substitution(expressions, values);
    }
    
}
