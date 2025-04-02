package shrutosom.bala.csv2api.batchprocessing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.ClassPathResource;

import com.opencsv.CSVReader;

public class CSVUploadProcessor implements ItemProcessor<CSVModel, CSVModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUploadProcessor.class);

    @Override
    public CSVModel process(CSVModel item) throws Exception {
        String csvFileSource = "csv-dump/customers-100.csv";
        LOGGER.info("Reading CSV from file : {}", csvFileSource);
        List<String[]> csvSourceList = readLineByLine(csvFileSource);
        CSVModel csvModel = new CSVModel();
        csvModel.setColumns(csvSourceList.get(0));
        csvSourceList.remove(0); // This is an O(n) operation. Needs to be optimised
        csvModel.setData(csvSourceList);
        return csvModel;
    }

    public List<String[]> readLineByLine(String csvFileSource) throws Exception {
        InputStream inputStream = new ClassPathResource(csvFileSource).getInputStream();
        List<String[]> list = new ArrayList<>();
        try (Reader reader = new InputStreamReader(inputStream)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }

}
