/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author egorm
 */
public class FileManager {
    
    public static String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        if (lastIndexOfDot != -1 && lastIndexOfDot < filename.length() - 1) {
            return filename.substring(lastIndexOfDot + 1);
        }
        return ""; 
    }
        
    public static String read(String fileName, String archive, int encryptKey) throws IOException
    {
        String result = "";
        if (archive.equals("") || archive == null)
        {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String tmp = reader.readLine();
                while (tmp != null) {
                        result += tmp + "\n";
                        tmp = reader.readLine();
                }
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("open archive" + "\n");
            File file = new File(fileName);
            fileName = file.getName();
            archive = file.getParent() + "/" + archive; // Путь к директории
            //result = ArchiveManager.dearchive(archive, fileName);
            System.out.println("result " + result + "\n");
        }
        if (encryptKey != 0)
        {
            result = EncryptManager.encrypt(result, encryptKey);
        }
        return result;
    }
    
    public static void write(String fileName, String archive, int encryptKey, String data) throws IOException
    {
        if (encryptKey != 0)
        {
            data = EncryptManager.encrypt(data, encryptKey);
        }
        if (archive.equals("") || archive == null)
        {
            try(FileWriter writer = new FileWriter(fileName, false)) {
                writer.write(data);
            }
            catch(IOException ex){    
                System.out.println(ex.getMessage());
            }   
        }
        else
        {
            System.out.println("open archive" + "\n");
            File file = new File(fileName);
            fileName = file.getName();
            archive = file.getParent() + "/" + archive; // Путь к директории
            //ArchiveManager.archive(archive, fileName, data);
        }
    }
    
}
