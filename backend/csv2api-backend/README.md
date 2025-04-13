# Project Setup

1. Should be a simple gradle import. Below are the recommended config in 'settings.json'

```json
{
    "java.compile.nullAnalysis.mode": "disabled",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-17",
            "path": "/home/sbala/Documents/Tools/jdk/jdk-17.0.0.1/"
        }
    ],
    "java.import.gradle.java.home": "/home/sbala/Documents/Tools/jdk/jdk-17.0.0.1/"
}
```

# Architecture

1. `csv-records.json` is read to determine the number of csv file & their structure. Data is then read & pushed to DB.

## Curl 

1. getTableData

```ps
curl --request GET \
  --url http://localhost:8080/getTableData
```

2. getCSVRecordList

```ps
curl --request GET \
  --url http://localhost:8080/getTableData/customers%20limit%202
```