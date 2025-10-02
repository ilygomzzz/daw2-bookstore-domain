package es.javierserrano.domain.data.loader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResourceDataLoader {
    protected String csvFile;

    public ResourceDataLoader(String csvFiler) {
        this.csvFile = csvFiler;
    }

    private static final CSVFormat CSV_FORMAT = CSVFormat.MYSQL.builder()
            .setHeader()
            .setDelimiter(',')
            .setQuote('"')
            .get();

    protected List<CSVRecord> loadDataFromCsv() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile)) {
            if (inputStream == null) {
                System.out.println("File not found: " + csvFile);
                throw new IllegalArgumentException("File not found: " + csvFile);
            }

            try (CSVParser csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, CSV_FORMAT)) {
                return csvParser.getRecords();
            }
        } catch (Exception e) {
            System.out.println("Error loading CSV file: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Optional<CSVRecord> findCsvRecordById(long id) {
        List<CSVRecord> records = loadDataFromCsv();
        return records.stream()
                .filter(r -> r.get("id").equals(id))
                .findFirst();
    }

    public List<CSVRecord> findAllCsvRecordsByIds(Long[] ids) {
        List<CSVRecord> records = loadDataFromCsv();
        return records.stream()
                .filter(record -> {
                    for (long id : ids) {
                        if (Long.parseLong(record.get("id")) == id) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
