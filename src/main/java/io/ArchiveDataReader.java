/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.IOException;
import archiver.Archiver;
import archiver.ZipArchiver;

/**
 *
 * @author egorm
 */
public class ArchiveDataReader implements DataReader {
    private final DataReader wrappedReader;
    private final Archiver archiver;

    public ArchiveDataReader(DataReader wrappedReader) {
        this.wrappedReader = wrappedReader;
        this.archiver = new ZipArchiver();
    }

    @Override
    public String read(String directoryPath, String archiveName, String fileName) throws IOException {
        if (archiveName != null && !archiveName.isEmpty()) {
            return archiver.decompress(directoryPath, archiveName, fileName);
        } else {
            return wrappedReader.read(directoryPath, archiveName, fileName);
        }
    }
}