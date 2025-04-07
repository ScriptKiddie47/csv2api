package shrutosom.bala.csv2api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CSVRecord(@JsonProperty("csvFileName") String csvFileName, @JsonProperty("fields") String[] fields) {

}
