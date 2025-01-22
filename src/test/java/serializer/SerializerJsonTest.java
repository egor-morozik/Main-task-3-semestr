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
public class SerializerJsonTest {
    
    private final Serializer SERIALIZER = new SerializerFactory().creatSerializer("json");

    /**
     * Test of serialize method, of class SerializerTxt.
     */
    @Test
    public void testSerialize() {
        System.out.println("serialize");
        Vector<String> expressions = new Vector();
        Vector<Double> results = new Vector();
        expressions.add("45+5");
        expressions.add("1+3");
        results.add(50.0);
        results.add(4.0);
        String expResult =  "{\"45+5\":50,\"1+3\":4}";
        String result = SERIALIZER.serialize(expressions, results);
        assertEquals(expResult, result);
    }

    /**
     * Test of deserialize method, of class SerializerTxt.
     */
    @Test
    public void testDeserialize() {
        System.out.println("deserialize");
        String data =   "{\n" +
                        "\"expression\":\"45*a+b\",   \n" +
                        "\"a\":5,\n" +
                        "\"b\":3\n" +
                        "}";
        Vector<String> expResult  = new Vector();
        expResult.add("45*5+3");
        Vector<String> result = SERIALIZER.deserialize(data);
        assertEquals(expResult, result);
    }
    
}
