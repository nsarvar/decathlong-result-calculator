# Decathlon result calculator

This program calculates the results of a Decathlon competition.

Input file is CSV format and path to file must be provided.
Output file is XML file. 

### Usage

`java -jar -Dfile='/path/to/file.csv' decathlon-calculator-1.0.jar`

Options:

- `-Dfile` - input file (CSV file by default)
- `-Dout` - path to write the result file  (XML file by default)


# Technical requirements

- JDK 11
- Maven
- Junit5