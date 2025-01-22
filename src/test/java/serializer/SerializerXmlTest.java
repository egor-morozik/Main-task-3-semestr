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

/**
 *
 * @author egorm
 */
public class SerializerXmlTest {
    
    private final Serializer SERIALIZER = new SerializerFactory().creatSerializer("xml");

    /**
     * Test of serialize method, of class SerializerTxt.
     */
    @Test
    public void testSerialize() {
        Vector<String> expressions = new Vector<>();
        Vector<Double> results = new Vector<>();
        
        expressions.add("2 + 2");
        results.add(4.0);
        
        expressions.add("3 * 3");
        results.add(9.0);
        
        SerializerXml serializerXml = new SerializerXml();
        
        // Тестируем метод serialize
        String xmlData = serializerXml.serialize(expressions, results);
        assertNotNull(xmlData, "Should return string");
        assertTrue(xmlData.contains("2 + 2"), "Should have '2 + 2'");
        assertTrue(xmlData.contains("4.0"), "Should haveе '4.0'");
    }

    /**
     * Test of deserialize method, of class SerializerTxt.
     */
    @Test
    public void testDeserialize() {
        System.out.println("deserialize");
        String data =   "<expressions>\n" +
                        "<expression>\n" +
                        "<value>\"45*a+b\"</value>\n" +
                        "<a>5</a>\n" +
                        "<b>3</b>\n" +
                        "</expression>\n" +
                        "<expression>\n" +
                        "<value>\"13*c+2*d\"</value>\n" +
                        "<c>3</c>\n" +
                        "<d>10</d>\n" +
                        "</expression>\n" +
                        "</expressions>";
        Vector<String> expResult  = new Vector();
        expResult.add("\"45*5+3\"");
        expResult.add("\"13*3+2*10\"");
        Vector<String> result = SERIALIZER.deserialize(data);
        assertEquals(expResult, result);
    }
    
}
