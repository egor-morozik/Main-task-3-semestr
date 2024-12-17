/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.util.Map;
import java.util.Vector;
import java.lang.String;
/**
 *
 * @author egorm
 */
public class Serializer {
    public Vector<String> substitution(Vector<String> expressions, Map<String, Integer> values)
    {
        Vector<String> newExpressions = new Vector<String>();
        for (int i = 0; i < expressions.size(); i++)
        {
            String someExpr = expressions.get(i);
            System.out.println(someExpr);
            for (int j = 0; j < expressions.get(i).length(); j++)
            {
                if (Character.isAlphabetic(someExpr.charAt(j)))
                {
                    someExpr = someExpr.substring(0, j) 
                            + Integer.toString(values.get(someExpr.substring(j, j+1)))
                            + someExpr.substring(j + 1);
                }
            }
            newExpressions.add(someExpr);
        }

        return newExpressions;
    }
    
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        return "";
    }
    
    public Vector<String> deserialize(String data)
    {
        return null;
    }
}
