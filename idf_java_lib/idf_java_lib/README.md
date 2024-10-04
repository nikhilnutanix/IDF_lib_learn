# Usage

Run the following command to generate the jar file:

```bash
mvn clean install -s settings.xml
```

Before running the jar file, mark `target/generated-sources/` as generated sources root.

Then you can run the jar file with the following command:

```bash
java -jar target/{jar_file_name}.jar
```