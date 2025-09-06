package tests.workWithFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import utils.ZipExtractorService;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTests {
    ZipExtractorService extractor = new ZipExtractorService();

    @Test
    void successExtractAndCheckPdfFileFromZip() throws Exception {
        PDF pdfFile = extractor.extractPdfFromZip("test.zip");
        assertTrue(pdfFile.numberOfPages > 0);
        assertTrue(pdfFile.text.contains("Тестовый PDF файл"), "PDF файл не содержит нужного текста");
    }

    @Test
    void successExtractAndCheckXlsFileFromZip() throws Exception {
        XLS xls = extractor.extractXlsFromZip("test.zip");
    }
}
