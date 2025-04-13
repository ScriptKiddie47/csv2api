package shrutosom.bala.csv2api.repository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import shrutosom.bala.csv2api.model.CSVRecord;

import static shrutosom.bala.csv2api.utils.Csv2APIUtils.csvRecordList;

@Repository
public class Csv2APIRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(Csv2APIRepository.class);
    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    public Csv2APIRepository(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void createAndUpdateTables() {

        try {
            Resource resource = resourceLoader.getResource("classpath:csv-records.json");
            ObjectMapper objectMapper = new ObjectMapper();
            csvRecordList = new ArrayList<>();
            csvRecordList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<CSVRecord>>() {
            });

            for (CSVRecord csvRow : csvRecordList) {

                String dropTableQuery = String.format("DROP TABLE %s IF EXISTS", csvRow.csvFileName());
                StringBuilder createTableQuery = new StringBuilder();
                createTableQuery.append("CREATE TABLE ");
                createTableQuery.append(csvRow.csvFileName() + " (");
                for (String s : csvRow.fields()) {
                    createTableQuery.append(s);
                    createTableQuery.append(" VARCHAR(255),");
                }
                createTableQuery.deleteCharAt(createTableQuery.length() - 1); // Remove last ","
                createTableQuery.append(" )");

                LOGGER.info(createTableQuery.toString());
                jdbcTemplate.execute(dropTableQuery);
                jdbcTemplate.execute(createTableQuery.toString());
                insertRows(csvRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertRows(CSVRecord csvRow) {
        int rowCounter = 0;
        String fileSource = "csv-dump/" + csvRow.csvFileName() + ".csv";
        try {
            InputStream inputStream = new ClassPathResource(fileSource).getInputStream();
            try (Reader reader = new InputStreamReader(inputStream)) {
                try (CSVReader csvReader = new CSVReader(reader)) {
                    csvReader.readNext(); // Skip line 1 of CSV
                    String[] line;
                    while ((line = csvReader.readNext()) != null) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("insert into ");
                        builder.append(csvRow.csvFileName());
                        builder.append("(");

                        for (String s : csvRow.fields()) {
                            builder.append(s);
                            builder.append(",");
                        }
                        builder.deleteCharAt(builder.length() - 1); // Remove last ","
                        builder.append(")");

                        builder.append(" values(");

                        for (String s : line) {
                            builder.append("'");
                            builder.append(s.replaceAll("'", "''"));
                            builder.append("'");
                            builder.append(",");

                        }
                        builder.deleteCharAt(builder.length() - 1); // Remove last ","
                        builder.append(")");
                        jdbcTemplate.update(builder.toString());
                        rowCounter++;
                    }
                }
            }
            LOGGER.info("Pushed {} rows to DB",rowCounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray fetchData(String query){
        
        String sqlQuery = "select * from " + query;
        LOGGER.info("Querrying : {}",sqlQuery);
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sqlQuery);
        JSONArray ja = new JSONArray();
        for (Map<String,Object> map : queryForList) {
            JSONObject jo = new JSONObject(map);
            ja.put(jo);
        }
        return ja;
    }


}
