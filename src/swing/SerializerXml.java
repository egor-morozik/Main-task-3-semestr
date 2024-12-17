/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.List;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author egorm
 */
public class SerializerXml extends Serializer {
    
    @Override
    public String serialize(Vector<String> expressions, Vector<Double> results)
    {
        String result = "";
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            org.w3c.dom.Element rootElement = doc.createElement("results");
            doc.appendChild(rootElement);

            for (int i = 0; i < expressions.size(); i++) {
                org.w3c.dom.Element someResult = doc.createElement("result");

                org.w3c.dom.Element expression = doc.createElement("expression");
                expression.appendChild(doc.createTextNode(expressions.get(i)));
                someResult.appendChild(expression);

                org.w3c.dom.Element value = doc.createElement("value");
                value.appendChild(doc.createTextNode(Double.toString(results.get(i))));
                someResult.appendChild(value);

                rootElement.appendChild(someResult);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            StreamResult consoleResult = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            
            transformer.transform(source, consoleResult);
            result = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public Vector<String> deserialize(String data)
    {
        Vector <String> expressions = new Vector <String>();
	Map<String, Integer> values = new HashMap<String, Integer>();
        try 
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Парсинг XML из строки
            org.xml.sax.InputSource is = new org.xml.sax.InputSource(new StringReader(data));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            
            Element root = document.getDocumentElement();
            System.out.println("Корневой элемент: " + root.getNodeName());
            
            NodeList nodeList = root.getChildNodes();
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Элемент: " + element.getNodeName());
                    
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("  " + childNode.getNodeName() + ": " + childNode.getTextContent());
                            if (childNode.getNodeName().equals("value")) {
                                expressions.add(childNode.getTextContent());
                            } else {
                                values.put(childNode.getNodeName(), Integer.valueOf(childNode.getTextContent()));
                            }
                        }
                    }
                }
            }            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SerializerXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SerializerXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializerXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return substitution(expressions, values);
    }
}
