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
public class EncryptedDataWriter implements DataWriter {
    private final DataWriter wrappedWriter;
    private final Encryptor encryptor;
    private final String encryptionKey;

    public EncryptedDataWriter(DataWriter wrappedWriter, Encryptor encryptor, String encryptionKey) {
        this.wrappedWriter = wrappedWriter;
        this.encryptor = encryptor;
        this.encryptionKey = encryptionKey;
    }

    @Override
    public void write(String directoryPath, String archiveName, String fileName, String data) throws IOException {
        if (encryptionKey != null && !encryptionKey.isEmpty()) {
            data = encryptor.encrypt(data, encryptionKey);
        }
        wrappedWriter.write(directoryPath, archiveName, fileName, data);
    }
}
