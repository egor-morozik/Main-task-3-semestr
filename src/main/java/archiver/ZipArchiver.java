/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archiver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author egorm
 */

public class ZipArchiver implements Archiver {
    @Override
    public void compress(String directoryPath, String archiveName, String fileName, String data) throws IOException {
        Path archivePath = Paths.get(directoryPath, archiveName);
        // Проверяем, существует ли архив
        boolean archiveExists = Files.exists(archivePath);

        try (FileSystem zipFs = FileSystems.newFileSystem(
                URI.create("jar:" + archivePath.toUri()),
                Map.of("create", String.valueOf(!archiveExists)))) {

            Path fileInZip = zipFs.getPath(fileName);
            if (Files.exists(fileInZip)) {
                throw new FileAlreadyExistsException("Файл уже существует в архиве: " + fileName);
            }

            Files.writeString(fileInZip, data);
        }
    }

    @Override
    public String decompress(String directoryPath, String archiveName, String fileName) throws IOException {
        Path archivePath = Paths.get(directoryPath, archiveName);
        if (!Files.exists(archivePath)) {
            throw new FileNotFoundException("Архив не найден: " + archivePath);
        }

        try (FileSystem zipFs = FileSystems.newFileSystem(URI.create("jar:" + archivePath.toUri()), Map.of("create", "false"))) {
            Path fileInZip = zipFs.getPath(fileName);
            if (!Files.exists(fileInZip)) {
                throw new FileNotFoundException("Файл не найден в архиве: " + fileName);
            }
            return Files.readString(fileInZip);
        }
    }
}
