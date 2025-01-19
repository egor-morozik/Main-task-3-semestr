/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sub;

/**
 *
 * @author egorm
 */
public interface Encryptor {
    String encrypt(String data, String key);
    String decrypt(String data, String key);
}
