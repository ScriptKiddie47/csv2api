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