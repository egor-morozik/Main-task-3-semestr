/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package archiver;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class ZipArchiverTest {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String ARCHIVE_NAME = "testArchive.zip";
    private static final String FILE_NAME = "testFile.txt";
    private static final String FILE_CONTENT = "Hello, world!";
    private final ZipArchiver archiver = new ZipArchiver();

    private Path archivePath;

    @BeforeEach
    void setUp() {
        archivePath = Paths.get(TEMP_DIR, ARCHIVE_NAME);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(archivePath)) {
            Files.delete(archivePath);
        }
    }

    @Test
    void testCompressAndDecompress_Success() throws IOException {
        // Compress
        archiver.compress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME, FILE_CONTENT);

        assertTrue(Files.exists(archivePath), "Архив должен быть создан.");

        // Decompress
        String decompressedContent = archiver.decompress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME);

        assertEquals(FILE_CONTENT, decompressedContent, "Декодированное содержимое должно совпадать с исходным.");
    }

    @Test
    void testCompress_FileAlreadyExists() throws IOException {
        archiver.compress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME, FILE_CONTENT);

        assertThrows(FileAlreadyExistsException.class, () ->
                archiver.compress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME, "Данные для перезаписи"),
                "Должно выбрасываться исключение, если файл уже существует в архиве.");
    }

    @Test
    void testDecompress_FileNotFoundInArchive() throws IOException {
        archiver.compress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME, FILE_CONTENT);

        // Попытка декомпрессии несуществующего файла
        assertThrows(FileNotFoundException.class, () ->
                archiver.decompress(TEMP_DIR, ARCHIVE_NAME, "nonexistentFile.txt"),
                "Должно выбрасываться исключение, если файл отсутствует в архиве.");
    }

    @Test
    void testDecompress_ArchiveNotFound() {
        assertThrows(FileNotFoundException.class, () ->
                archiver.decompress(TEMP_DIR, "nonexistentArchive.zip", FILE_NAME),
                "Должно выбрасываться исключение, если архив не найден.");
    }

    @Test
    void testCompressAndDecompress_MultipleFiles() throws IOException {
        String anotherFileName = "anotherFile.txt";
        String anotherFileContent = "Another file content.";

        // Compress first file
        archiver.compress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME, FILE_CONTENT);

        // Compress second file
        archiver.compress(TEMP_DIR, ARCHIVE_NAME, anotherFileName, anotherFileContent);

        // Decompress and validate both files
        String decompressedContent1 = archiver.decompress(TEMP_DIR, ARCHIVE_NAME, FILE_NAME);
        String decompressedContent2 = archiver.decompress(TEMP_DIR, ARCHIVE_NAME, anotherFileName);

        assertEquals(FILE_CONTENT, decompressedContent1, "Содержимое первого файла должно совпадать.");
        assertEquals(anotherFileContent, decompressedContent2, "Содержимое второго файла должно совпадать.");
    }
}
