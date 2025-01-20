/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encryption;

/**
 *
 * @author egorm
 */
public class BasicEncryptor implements Encryptor {
    @Override
    public String encrypt(String data, String key) {
        // Простая реализация шифрования (например, XOR)
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append((char) (data.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encrypted.toString();
    }

    @Override
    public String decrypt(String data, String key) {
        // Дешифрование идентично шифрованию (симметричный XOR)
        return encrypt(data, key);
    }
}
