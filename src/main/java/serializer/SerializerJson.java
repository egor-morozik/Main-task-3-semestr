/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

/**
 *
 * @author egorm
 */
public class SerializerJson extends Serializer {
    
    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        JSONObject sampleObject = new JSONObject(); 
        for (int i=0; i < expressions.size(); i++)
        {
                sampleObject.put(expressions.get(i), results.get(i)); 
        }		 
        return sampleObject.toString();
    }
    
    @Override
    public Vector<String> deserialize(String data)
    {
        Vector <String> expressions = new Vector <String>();
	Map<String, Integer> values = new HashMap<String, Integer>();
        JSONObject jsonObject = new JSONObject(data);
        for (Iterator<String> it = jsonObject.keys(); it.hasNext();) 
        {
            String key = it.next();
            if (key.equals("expression"))
            {
                expressions.add((String) jsonObject.get(key));
            }
            else
            {
                values.put(key, (Integer) jsonObject.get(key));
                System.out.println(key);
            }
        }
        return substitution(expressions, values);
    }
}
