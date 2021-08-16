package com.nsarvar.utils;

import java.time.Duration;
import java.util.regex.Pattern;

public class UnitParser {

    private static final String FORMAT = "^(\\d{2}).(\\d{2})$";
    private static final String FORMAT_WITH_MINUTES_1 = "^(\\d{1,2}).(\\d{2}).(\\d{2})$";
    private static final String FORMAT_WITH_MINUTES_2 = "^(\\d{1,2}):(\\d{2}).(\\d{2})$";

    public static double parseToCentimeter(String p) {
        return Double.parseDouble(p) * 100;
    }

    public static double parseMeter(String p) {
        return Double.parseDouble(p);
    }

    public static double parseMinutesToSeconds(String p) {
        String isoString;
        if (Pattern.matches(FORMAT_WITH_MINUTES_1, p)) {
            isoString = p.replaceFirst(FORMAT_WITH_MINUTES_1, "PT$1M$2.$3S");
        } else if (Pattern.matches(FORMAT_WITH_MINUTES_2, p)) {
            isoString = p.replaceFirst(FORMAT_WITH_MINUTES_2, "PT$1M$2.$3S");
        } else {
            isoString = p.replaceFirst(FORMAT, "PT$1.$2S");
        }

        Duration duration = Duration.parse(isoString);
        double l = duration.toMillis();
        return l / 1000;
    }

}
