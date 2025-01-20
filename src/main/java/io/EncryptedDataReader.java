/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.IOException;
import encryption.Encryptor;

/**
 *
 * @author egorm
 */
public class EncryptedDataReader implements DataReader {
    private final DataReader wrappedReader;
    private final Encryptor encryptor;
    private final String encryptionKey;

    public EncryptedDataReader(DataReader wrappedReader, Encryptor encryptor, String encryptionKey) {
        this.wrappedReader = wrappedReader;
        this.encryptor = encryptor;
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String read(String directoryPath, String archiveName, String fileName) throws IOException {
        String data = wrappedReader.read(directoryPath, archiveName, fileName);
        if (encryptionKey != null && !encryptionKey.isEmpty()) {
            return encryptor.decrypt(data, encryptionKey);
        }
        return data;
    }
}
