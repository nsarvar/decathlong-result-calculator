# Decathlon result calculator



This program calculates the results of a Decathlon competition.

### Input

Input file is CSV format and path to file must be provided.

### Output

An XML file containing all the athletes in ascending order of their places. 
Athletes have all the result data from the CSV file plus the calculated total score and the place in the competition.
In case of equal scores, athletes share the places, e.g. 3-4 and 3-4 instead of 3 and 4.

### Usage

Build

`mvn install`

Test

`mvn test`

Run

`java -jar -Dfile='/path/to/file.csv' decathlon-calculator-1.0.jar`

Options:

- `-Dfile` - input file (CSV file by default)
- `-Dout` - path to write the result file  (XML file by default)

---

- JDK 11
- Maven
- Junit5