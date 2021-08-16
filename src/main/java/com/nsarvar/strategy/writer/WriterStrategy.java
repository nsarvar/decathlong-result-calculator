package com.nsarvar.strategy.writer;

import com.nsarvar.payload.Athlete;

import java.util.List;

public interface WriterStrategy {
    String writeToFile(List<Athlete> athletes, String outputFilePath);
}
