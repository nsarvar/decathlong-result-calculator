package com.nsarvar.strategy.reader;

import com.nsarvar.payload.Athlete;

import java.util.List;

public interface ReaderStrategy {
    List<Athlete> readFile();
}
