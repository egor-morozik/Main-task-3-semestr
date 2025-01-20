/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.IOException;
import archiver.Archiver;

/**
 *
 * @author egorm
 */
public class ArchiveDataWriter implements DataWriter {
    private final DataWriter wrappedWriter;
    private final Archiver archiver;

    public ArchiveDataWriter(DataWriter wrappedWriter, Archiver archiver) {
        this.wrappedWriter = wrappedWriter;
        this.archiver = archiver;
    }

    @Override
    public void write(String directoryPath, String archiveName, String fileName, String data) throws IOException {
        if (archiveName != null && !archiveName.isEmpty()) {
            archiver.compress(directoryPath, archiveName, fileName, data);
        } else {
            wrappedWriter.write(directoryPath, archiveName, fileName, data);
        }
    }
}
