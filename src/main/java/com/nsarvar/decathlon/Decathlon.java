package com.nsarvar.decathlon;

import com.nsarvar.payload.Athlete;
import com.nsarvar.utils.UnitParser;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.nsarvar.decathlon.Decathlon.EventType.FIELD;
import static com.nsarvar.decathlon.Decathlon.EventType.TRACK;
import static com.nsarvar.decathlon.Decathlon.Unit.*;

public class Decathlon {

    public enum Event {
        E_100M(TRACK, SECONDS, 25.4347, 18, 1.81),
        E_LONG_JUMP(FIELD, CENTIMETER, 0.14354, 220, 1.40),
        E_SHOT_PUT(FIELD, METRE, 51.39, 1.5, 1.05),
        E_HIGH_JUMP(FIELD, CENTIMETER, 0.8465, 75, 1.42),
        E_400M(TRACK, SECONDS, 1.53775, 82, 1.81),
        E_100M_HURDLES(TRACK, SECONDS, 5.74352, 28.5, 1.92),
        E_DISCUS_THROW(FIELD, METRE, 12.91, 4.0, 1.1),
        E_POLE_VAUNT(FIELD, CENTIMETER, 0.2797, 100, 1.35),
        E_JAVELIN_THROW(FIELD, METRE, 10.14, 7.0, 1.08),
        E_1500M(TRACK, SECONDS, 0.03768, 480, 1.85);

        private final EventType eventType;
        private final Unit unit;
        private final Double a;
        private final Double b;
        private final Double c;

        Event(EventType eventType, Unit unit, double a, double b, double c) {
            this.eventType = eventType;
            this.unit = unit;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public long calculateResult(String v) {
            double p = parseValue(v.trim());
            if (this.isTrackEvent())
                return Math.round(a * Math.pow((b - p), c));
            else
                return Math.round(a * Math.pow((p - b), c));
        }

        private double parseValue(String v) {
            switch (unit) {
                case METRE:
                    return UnitParser.parseMeter(v);
                case CENTIMETER:
                    return UnitParser.parseToCentimeter(v);
                case SECONDS:
                    return UnitParser.parseMinutesToSeconds(v);
            }
            throw new IllegalArgumentException("Something is wrong");
        }

        public boolean isTrackEvent() {
            return Objects.equals(eventType, TRACK);
        }
    }

    enum Unit {METRE, CENTIMETER, SECONDS}

    enum EventType {TRACK, FIELD}

    public static List<Athlete> rankAthletes(List<Athlete> athletes) {
        // sort the list
        athletes.sort(Comparator.comparing(Athlete::getTotalScore).reversed());

        for(int j = 0, i = 1; i <= athletes.size(); i++)
        {
            if(i == athletes.size() || !athletes.get(i).getTotalScore().equals(athletes.get(j).getTotalScore()))
            {
                String pos = (j + 1) + ((j + 1 == i) ? "" : "-" + i);
                while (j < i) athletes.get(j++).setRank(pos);
            }
        }
        return athletes;
    }
}
