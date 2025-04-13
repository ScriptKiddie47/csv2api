package shrutosom.bala.csv2api.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import shrutosom.bala.csv2api.model.CSVRecord;
import shrutosom.bala.csv2api.repository.Csv2APIRepository;
import shrutosom.bala.csv2api.utils.Csv2APIUtils;

@Service
public class Csv2APIService {

    private final Csv2APIRepository csv2apiRepository;

    public Csv2APIService(Csv2APIRepository csv2apiRepository) {
        this.csv2apiRepository = csv2apiRepository;
    }

    public List<CSVRecord> getCSVRecordList() {
        return Csv2APIUtils.csvRecordList;
    }

    public JSONArray getTableData(String query){
        return csv2apiRepository.fetchData(query);
    }

}
