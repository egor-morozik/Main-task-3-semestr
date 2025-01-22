/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package serializer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SerializerHtmlTest {

    @Test
    void testSerialize() {
        SerializerHtml serializer = new SerializerHtml();

        // Входные данные
        Vector<String> expressions = new Vector<>();
        expressions.add("x + y");
        expressions.add("a * b");

        Vector<Double> results = new Vector<>();
        results.add(5.0);
        results.add(10.0);

        // Выполняем сериализацию
        String htmlOutput = serializer.serialize(expressions, results);

        // Проверяем, что HTML содержит ожидаемые данные
        Document document = Jsoup.parse(htmlOutput);
        Elements resultElements = document.select("result");

        assertEquals(2, resultElements.size());

        Element firstResult = resultElements.get(0);
        assertEquals("x + y", firstResult.select("expression").text());
        assertEquals("5.0", firstResult.select("value").text());

        Element secondResult = resultElements.get(1);
        assertEquals("a * b", secondResult.select("expression").text());
        assertEquals("10.0", secondResult.select("value").text());
    }

    @Test
    void testSerializeWithMismatchedSizes() {
        SerializerHtml serializer = new SerializerHtml();

        // Входные данные
        Vector<String> expressions = new Vector<>();
        expressions.add("x + y");

        Vector<Double> results = new Vector<>();
        results.add(5.0);
        results.add(10.0);

        // Ожидаем исключение из-за разной длины массивов
        assertThrows(IllegalArgumentException.class, () -> serializer.serialize(expressions, results));
    }

    @Test
    void testDeserialize() {
        SerializerHtml serializer = new SerializerHtml();

        // HTML входные данные
        String htmlInput = """
        <expressions>
            <expression>
                <value>45*a+5*b</value>
                <a>1</a>
                <b>2</b>
            </expression>
            <expression>
                <value>3*c+d</value>
                <c>3</c>
                <d>3</d>
            </expression>
        </expressions>
        """;

        // Выполняем десериализацию
        Vector<String> deserializedExpressions = serializer.deserialize(htmlInput);

        // Ожидаемые данные
        Vector<String> expected = new Vector<>();
        expected.add("45*1+5*2");
        expected.add("3*3+3");

        assertEquals(expected, deserializedExpressions);
    }

}

