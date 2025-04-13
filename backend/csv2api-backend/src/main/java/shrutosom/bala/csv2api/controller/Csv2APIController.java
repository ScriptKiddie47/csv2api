package shrutosom.bala.csv2api.controller;

import org.springframework.web.bind.annotation.RestController;

import shrutosom.bala.csv2api.model.CSVRecord;
import shrutosom.bala.csv2api.service.Csv2APIService;

import java.util.List;

import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Csv2APIController {

    private final Csv2APIService service;

    public Csv2APIController(Csv2APIService service) {
        this.service = service;
    }

    @GetMapping("/getCSVRecordList")
    public List<CSVRecord> getCSVRecordList() {
        return service.getCSVRecordList();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getTableData/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTableData(@PathVariable String query) {
        JSONArray tableData = service.getTableData(query);
        return tableData.toString();

    }

}
