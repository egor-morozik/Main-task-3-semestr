/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializer;

/**
 *
 * @author egorm
 */
public class SerializerFactory {
    public static Serializer creatSerializer(String type) {
        Serializer serializer = null;
        switch (type) {
            case "txt" -> serializer = new SerializerTxt();
            case "json" -> serializer = new SerializerJson();
            case "xml" -> serializer = new SerializerXml();
            case "yaml" -> serializer = new SerializerYaml();
            case "html" -> serializer = new SerializerHtml();
        }
        return serializer;
    }
}
