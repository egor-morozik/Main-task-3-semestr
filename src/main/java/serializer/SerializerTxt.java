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
        Vector <String> expressions = new Vector <String>();
	Map<String, Integer> values = new HashMap<String, Integer>();
        String[] lines = data.split("\\n");
        for (int i = 0; i < lines.length; i++)
        {
            System.out.println("i line" + lines[i] + "\n");
            if (lines[i].indexOf("=") != -1) 
            {
                values.put(lines[i].substring(0, lines[i].indexOf("=")), 
                                        Integer. parseInt(
                                        lines[i].substring(lines[i].indexOf("=")+1)));
            }
            else
            {
                expressions.add(lines[i]);
            }
        }
        return substitution(expressions, values);
    }
}
