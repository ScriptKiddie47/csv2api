package shrutosom.bala.csv2api.batchprocessing;

import java.util.List;

public class CSVModel {
    String[] columns;
    List<String[]> data;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

}
