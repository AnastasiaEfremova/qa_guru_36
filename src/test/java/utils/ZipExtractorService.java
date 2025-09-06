package utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractorService {

    /**
     * Ищет первый PDF файл в ZIP архиве и возвращает как объект PDF
     */
    public PDF extractPdfFromZip(String zipResourcePath) throws IOException {
        InputStream zipStream = getClass().getClassLoader().getResourceAsStream(zipResourcePath);

        if (zipStream == null) {
            throw new IOException("ZIP файл не найден: " + zipResourcePath);
        }

        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".pdf")) {
                    return createPdfFromStream(zis, entry.getName());
                }
                zis.closeEntry();
            }
        }

        throw new IOException("PDF файл не найден в архиве");
    }

    /**
     * Создает объект PDF из InputStream
     */
    private PDF createPdfFromStream(InputStream pdfStream, String fileName) throws IOException {
        File tempFile = File.createTempFile("pdf_temp", ".pdf");
        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = pdfStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }

        return new PDF(tempFile);
    }

    /**
     * Ищет первый XLSX файл в ZIP архиве и возвращает как объект XLS
     */
    public XLS extractXlsFromZip(String zipResourcePath) throws IOException {
        InputStream zipStream = getClass().getClassLoader().getResourceAsStream(zipResourcePath);

        if (zipStream == null) {
            throw new IOException("ZIP файл не найден: " + zipResourcePath);
        }

        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && isExcelFile(entry.getName())) {
                    return createXlsFromStream(zis, entry.getName());
                }
                zis.closeEntry();
            }
        }

        throw new IOException("XLS файл не найден в архиве");
    }

    /**
     * Проверяет, является ли файл Excel файлом
     */
    private boolean isExcelFile(String fileName) {
        String lowerName = fileName.toLowerCase();
        return lowerName.endsWith(".xls") ||
                lowerName.endsWith(".xlsx") ||
                lowerName.endsWith(".xlsm");
    }

    /**
     * Создает объект XLS из InputStream
     */
    private XLS createXlsFromStream(InputStream xlsStream, String fileName) throws IOException {
        String extension = fileName.contains(".") ?
                fileName.substring(fileName.lastIndexOf(".")) : ".xls";
        File tempFile = File.createTempFile("xls_temp", extension);
        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = xlsStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }

        return new XLS(tempFile);
    }

    /**
     * Ищет первый CSV файл в ZIP архиве и возвращает как Reader для OpenCSV
     */
    public Reader extractCsvAsReader(String zipResourcePath) throws IOException {
        InputStream zipStream = getClass().getClassLoader().getResourceAsStream(zipResourcePath);

        if (zipStream == null) {
            throw new IOException("ZIP файл не найден: " + zipResourcePath);
        }

        byte[] zipData = zipStream.readAllBytes();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipData))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && isCsvFile(entry.getName())) {
                    // Читаем содержимое CSV файла
                    byte[] csvData = zis.readAllBytes();
                    zis.closeEntry();
                    // Возвращаем Reader из byte array
                    return new InputStreamReader(new ByteArrayInputStream(csvData), StandardCharsets.UTF_8);
                }
                zis.closeEntry();
            }
        }

        throw new IOException("CSV файл не найден в архиве");
    }

    /**
     * Проверяет, является ли файл CSV файлом
     */
    private boolean isCsvFile(String fileName) {
        String lowerName = fileName.toLowerCase();
        return lowerName.endsWith(".csv");
    }
}