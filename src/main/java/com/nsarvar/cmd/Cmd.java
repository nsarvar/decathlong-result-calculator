package com.nsarvar.cmd;

import com.nsarvar.decathlon.Decathlon;
import com.nsarvar.payload.Athlete;
import com.nsarvar.strategy.reader.CSVReaderStrategy;
import com.nsarvar.strategy.reader.ReaderStrategy;
import com.nsarvar.strategy.writer.WriterStrategy;
import com.nsarvar.strategy.writer.XmlWriterStrategy;

import java.util.List;
import java.util.concurrent.Callable;

public class Cmd implements Callable<String> {
    private final String filepath;
    private final String resultFilePath;
    private final ReaderStrategy readerStrategy;
    private final WriterStrategy writerStrategy;

    public Cmd() {
//        filepath = System.getProperty("file");
        resultFilePath = System.getProperty("out", "");
        filepath = "/Users/nsarvar/Downloads/results.csv";
//        if (filepath == null || filepath.isEmpty())
//            throw new IllegalArgumentException("File is not provided");

        readerStrategy = new CSVReaderStrategy(filepath);
        writerStrategy = new XmlWriterStrategy();
    }

    @Override
    public String call() {
        System.out.println("Parsing file " + filepath);
        List<Athlete> athletes = readerStrategy.readFile();

        System.out.println("Writing to XML file");
        return writerStrategy.writeToFile(Decathlon.rankAthletes(athletes), resultFilePath);
    }
}