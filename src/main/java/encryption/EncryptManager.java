/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encryption;

/**
 *
 * @author egorm
 */
public class EncryptManager {
    
    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char charAt : text.toCharArray()) {
            if (Character.isLetter(charAt)) { 
                char base = Character.isLowerCase(charAt) ? 'a' : 'A';
                encryptedText.append((char) ((charAt - base + key) % 26 + base));
            } else {
                encryptedText.append(charAt); 
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char charAt : encryptedText.toCharArray()) {
            if (Character.isLetter(charAt)) { 
                char base = Character.isLowerCase(charAt) ? 'a' : 'A';
                decryptedText.append((char) ((charAt - base - key + 26) % 26 + base));
            } else {
                decryptedText.append(charAt); 
            }
        }
        return decryptedText.toString();
    }
}
