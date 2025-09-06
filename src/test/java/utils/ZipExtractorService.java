package utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractorService {

    /**
     * ���� ������ PDF ���� � ZIP ������ � ���������� ��� ������ PDF
     */
    public PDF extractPdfFromZip(String zipResourcePath) throws IOException {
        InputStream zipStream = getClass().getClassLoader().getResourceAsStream(zipResourcePath);

        if (zipStream == null) {
            throw new IOException("ZIP ���� �� ������: " + zipResourcePath);
        }

        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".pdf")) {
                    // ��������� �� ��������� ���� � ������� PDF ������
                    return createPdfFromStream(zis, entry.getName());
                }
                zis.closeEntry();
            }
        }

        throw new IOException("PDF ���� �� ������ � ������");
    }


    /**
     * ������� ������ PDF �� InputStream
     */
    private PDF createPdfFromStream(InputStream pdfStream, String fileName) throws IOException {
        // ������� ��������� ����
        File tempFile = File.createTempFile("pdf_temp", ".pdf");
        tempFile.deleteOnExit(); // ������� ��� ���������� ���������

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = pdfStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }

        // ������� ������ PDF �� ���������� �����
        return new PDF(tempFile);
    }

    /**
     * ���� ������ XLS ���� � ZIP ������ � ���������� ��� ������ XLS
     */
    public XLS extractXlsFromZip(String zipResourcePath) throws IOException {
        InputStream zipStream = getClass().getClassLoader().getResourceAsStream(zipResourcePath);

        if (zipStream == null) {
            throw new IOException("ZIP ���� �� ������: " + zipResourcePath);
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

        throw new IOException("XLS ���� �� ������ � ������");
    }

    /**
     * ���������, �������� �� ���� Excel ������
     */
    private boolean isExcelFile(String fileName) {
        String lowerName = fileName.toLowerCase();
        return lowerName.endsWith(".xls") ||
                lowerName.endsWith(".xlsx") ||
                lowerName.endsWith(".xlsm");
    }

    /**
     * ������� ������ XLS �� InputStream
     */
    private XLS createXlsFromStream(InputStream xlsStream, String fileName) throws IOException {
        // ������� ��������� ����
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

        // ������� ������ XLS �� ���������� �����
        return new XLS(tempFile);
    }

}