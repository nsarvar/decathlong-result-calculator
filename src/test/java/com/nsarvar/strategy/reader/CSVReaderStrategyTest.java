package com.nsarvar.strategy.reader;

import com.nsarvar.decathlon.Decathlon;
import com.nsarvar.payload.Athlete;
import com.nsarvar.payload.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CSVReaderStrategyTest {
    private ReaderStrategy readerStrategy;
    int[] totalScores = {4205, 3205, 3499, 3104, 3499, 3499};
    String[] names = {"John Smith", "Jane Doe", "Jaan Lepp", "Foo Bar", "John Stones", "Eldor Shomurodov"};
    String[] ranks = {"1", "2-4", "2-4", "2-4", "5", "6"};

    @BeforeEach
    void setUp() {
        readerStrategy = new CSVReaderStrategy("src/test/resources/results.csv");
    }

    @Test
    void readFile() {
        List<Athlete> athletes = readerStrategy.readFile();
        assertEquals(athletes.size(), 6);

        int i = 0;
        for (Athlete athlete : athletes) {
            assertTrue(athlete.getTotalScore() > 0);
            assertEquals(athlete.getName(), names[i]);
            assertEquals(athlete.getTotalScore(), totalScores[i]);
            List<Result> results = athlete.getResults();
            assertEquals(results.size(), 10);
            assertEquals(results.get(0).getEvent(), Decathlon.Event.E_100M);
            assertEquals(results.get(1).getEvent(), Decathlon.Event.E_LONG_JUMP);
            assertEquals(results.get(2).getEvent(), Decathlon.Event.E_SHOT_PUT);
            assertEquals(results.get(3).getEvent(), Decathlon.Event.E_HIGH_JUMP);
            assertEquals(results.get(4).getEvent(), Decathlon.Event.E_400M);
            assertEquals(results.get(5).getEvent(), Decathlon.Event.E_100M_HURDLES);
            assertEquals(results.get(6).getEvent(), Decathlon.Event.E_DISCUS_THROW);
            assertEquals(results.get(7).getEvent(), Decathlon.Event.E_POLE_VAUNT);
            assertEquals(results.get(8).getEvent(), Decathlon.Event.E_JAVELIN_THROW);
            assertEquals(results.get(9).getEvent(), Decathlon.Event.E_1500M);
            i++;
        }

    }

    @Test
    void testRanking() {
        var athletes = Decathlon.rankAthletes(readerStrategy.readFile());
        assertEquals(6, athletes.size());

        int i = 0;
        for (Athlete athlete : athletes) {
            assertEquals(ranks[i], athlete.getRank());
            i++;
        }
    }
}