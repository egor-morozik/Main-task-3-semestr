/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.app;

/**
 *
 * @author egorm
 */
public class SerializerFactory {
    public static Serializer creatSerializer(SerializerType type) {
        Serializer serializer = null;
        switch (type) {
            case txt:
                serializer = new SerializerTxt();
                break;
            case json:
                serializer = new SerializerJson();
                break;
            case xml:
                serializer = new SerializerXml();
                break;
            case yaml:
                serializer = new SerializerYaml();
                break;
            case html:
                serializer = new SerializerHtml();
                break;
        }
        return serializer;
    }
}
