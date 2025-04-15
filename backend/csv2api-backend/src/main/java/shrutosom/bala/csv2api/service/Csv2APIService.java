package shrutosom.bala.csv2api.service;

import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import shrutosom.bala.csv2api.model.CSVRecord;
import shrutosom.bala.csv2api.repository.Csv2APIRepository;
import shrutosom.bala.csv2api.utils.Csv2APIUtils;

@Service
public class Csv2APIService {

    private final Csv2APIRepository csv2apiRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(Csv2APIService.class);

    public Csv2APIService(Csv2APIRepository csv2apiRepository) {
        this.csv2apiRepository = csv2apiRepository;
    }

    public List<CSVRecord> getCSVRecordList() {
        LOGGER.info("Fetching CSV Record List of size: {}",Csv2APIUtils.csvRecordList.size());
        return Csv2APIUtils.csvRecordList;
    }

    public JSONArray getTableData(String query){
        return csv2apiRepository.fetchData(query);
    }

}
