package shrutosom.bala.csv2api.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class Csv2APIService {

    public String fetchCSVData() {
        try {
            List<String[]> lineByLine = readLineByLine();
            for (String[] s1 : lineByLine) {
                System.out.println();
                for (String s2 : s1) {
                    System.out.print(s2+ ",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello";
    }

    public List<String[]> readLineByLine() throws Exception {
        InputStream inputStream = new ClassPathResource("csv-dump/customers-100.csv").getInputStream();
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
