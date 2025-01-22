/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package encryption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author egorm
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasicEncryptorTest {

    private final BasicEncryptor ENCRYPTOR = new BasicEncryptor();

    @Test
    void testEncryptAndDecrypt_SimpleCase() {
        String data = "hello";
        String key = "key";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertNotEquals(data, encrypted, "Encrypted data should not match original data.");
        assertEquals(data, decrypted, "Decrypted data should match original data.");
    }

    @Test
    void testEncryptAndDecrypt_EmptyString() {
        String data = "";
        String key = "key";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertEquals("", encrypted, "Encrypted empty string should remain empty.");
        assertEquals("", decrypted, "Decrypted empty string should remain empty.");
    }

    @Test
    void testEncryptAndDecrypt_SingleCharacter() {
        String data = "a";
        String key = "key";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertNotEquals(data, encrypted, "Encrypted data should not match original data.");
        assertEquals(data, decrypted, "Decrypted data should match original data.");
    }

    @Test
    void testEncryptAndDecrypt_KeyLongerThanData() {
        String data = "test";
        String key = "longkey";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertNotEquals(data, encrypted, "Encrypted data should not match original data.");
        assertEquals(data, decrypted, "Decrypted data should match original data.");
    }

    @Test
    void testEncryptAndDecrypt_KeyShorterThanData() {
        String data = "longerdata";
        String key = "key";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertNotEquals(data, encrypted, "Encrypted data should not match original data.");
        assertEquals(data, decrypted, "Decrypted data should match original data.");
    }

    @Test
    void testEncryptAndDecrypt_SpecialCharacters() {
        String data = "data_with_special_chars!@#123";
        String key = "key";
        String encrypted = ENCRYPTOR.encrypt(data, key);
        String decrypted = ENCRYPTOR.decrypt(encrypted, key);

        assertNotEquals(data, encrypted, "Encrypted data should not match original data.");
        assertEquals(data, decrypted, "Decrypted data should match original data.");
    }
}
