package tests.workWithFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import models.CarBrand;
import models.CarModel;
import org.junit.jupiter.api.Test;
import utils.ZipExtractorService;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FilesTests {
    ZipExtractorService extractor = new ZipExtractorService();

    @Test
    void successExtractAndCheckPdfFileFromZip() throws Exception {
        PDF pdfFile = extractor.extractPdfFromZip("test.zip");
        assertTrue(pdfFile.numberOfPages > 0);
        assertTrue(pdfFile.text.contains("Quisque volutpat condimentum velit."), "pdf не содержит нужный текст.");
    }

    @Test
    void successExtractAndCheckXlsxFileFromZip() throws Exception {
        XLS xlsx = extractor.extractXlsFromZip("test.zip");
        assertTrue(xlsx.excel.getNumberOfSheets() > 0);
        String cellValue = xlsx.excel.getSheetAt(1).getRow(5).getCell(1).getStringCellValue();
        assertTrue(cellValue.contains("Ford Transit"));
    }

    @Test
    void successExtractAndCheckCsvFileFromZip() throws Exception {
        CSVReader csvReader = new CSVReader(extractor.extractCsvAsReader("test.zip"));
        List<String[]> data = csvReader.readAll();
        assertTrue(data.size() > 0);
        assertArrayEquals(new String[]{"Ivan Ivanov", "ivan@example.com", "8-999-123-45-67"}, data.get(1));
    }

    @Test
    void successCheckJsonFile() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream jsonStream = getClass().getClassLoader()
                .getResourceAsStream("carBrand.json");

        assertNotNull(jsonStream, "JSON не найден в resources");

        CarBrand carBrand = objectMapper.readValue(jsonStream, CarBrand.class);

        assertEquals("Toyota", carBrand.getBrand());
        assertEquals("Japan", carBrand.getCountry());
        assertEquals(1937, carBrand.getFounded());

        List<String> features = carBrand.getFeatures();
        assertNotNull(features);
        assertEquals(3, features.size());
        assertTrue(features.contains("Hybrid"));
        assertTrue(features.contains("Safety Sense"));
        assertTrue(features.contains("All-Wheel Drive"));

        List<CarModel> models = carBrand.getModels();
        assertNotNull(models);
        assertEquals(6, models.size());

        CarModel firstModel = models.get(0);
        assertEquals("Camry", firstModel.getName());
        assertEquals("Sedan", firstModel.getType());
        assertEquals(2023, firstModel.getYear());
    }

    @Test
    void testJsonStructure() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream jsonStream = getClass().getClassLoader()
                .getResourceAsStream("carBrand.json");

        CarBrand carBrand = objectMapper.readValue(jsonStream, CarBrand.class);

        List<String> modelNames = carBrand.getModels().stream()
                .map(CarModel::getName)
                .collect(Collectors.toList());

        assertTrue(modelNames.contains("Camry"));
        assertTrue(modelNames.contains("Corolla"));
        assertTrue(modelNames.contains("RAV4"));
        assertTrue(modelNames.contains("Highlander"));
        assertTrue(modelNames.contains("Prius"));
        assertTrue(modelNames.contains("Land Cruiser"));
    }
}
