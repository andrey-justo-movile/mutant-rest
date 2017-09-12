# Application mutant-rest 

This application runs a API Rest to check if a dna string is a mutant or not:
```sh
curl -X POST \
  http://localhost:8082/mutant \
  -d '{ "dna": ["ATCG", "CTGA", "AGTC", "ATGC"]}'
```
It also generates a statistics reports:
```sh
curl -X GET http://localhost:8082/stats
```

# Installation Requirements
- git clone ${project}
- mongodb 3.2.+
- Java 8

# Execution
- Go into project''s folder
```sh
./gradlew mutant-api:run
```
- Or if you want to listen a port to debug the application
```sh
./gradlew mutant-api:run --debug-jvm
```

# Test, Code Coverage and check Bad Pratices
```sh
./gradlew clean findbugsAll
```

- The command will generate 3 types of reports
-- 1 - executes JUnit Tests and generate html files in mutant-api/build/reports/tests/test/index.html and mutant-core/build/reports/tests/test/index.html
-- 2 - executes Jacoco Code Coverage and generate html files in mutant-api/build/reports/jacoco/test/html/index.html and mutant-core/build/reports/jacoco/test/html/index.html. Also it generates a .exec file to use in a Sonar integration
-- 3 - executes Findbugs plugin and generate html files in mutant-api/build/reports/findbugs/main.html mutant-core/build/reports/findbugs/main.html


# Deploy
