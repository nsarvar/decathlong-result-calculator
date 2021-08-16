package com.nsarvar.strategy.reader;

import com.nsarvar.payload.Athlete;
import com.nsarvar.payload.Result;
import com.nsarvar.decathlon.Decathlon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReaderStrategy implements ReaderStrategy {
    private static final String DEFAULT_SEPARATOR = ";";

    private final String filePath;

    public CSVReaderStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Athlete> readFile() {
        List<Athlete> athletes = new ArrayList<>();
        File inputF = new File(filePath);
        //  We assume that the CSV file format is plain simple and does not contain any embedded commas,
        //  double quotes,and line breaks
        try (BufferedReader br = new BufferedReader(new FileReader(inputF))) {
            athletes = br.lines()
                    .filter(s -> !s.trim().isEmpty())
                    .map(line -> line.split(DEFAULT_SEPARATOR))
                    .map(this::mapToAthlete)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return athletes;
    }

    private Athlete mapToAthlete(String[] cells) {
        Athlete athlete = new Athlete();
        athlete.setName(cells[0]);
        IntStream.range(1, cells.length)
                .mapToObj(i -> new Result(Decathlon.Event.values()[i - 1], cells[i]))
                .forEach(athlete::addResult);
        return athlete;
    }
}
