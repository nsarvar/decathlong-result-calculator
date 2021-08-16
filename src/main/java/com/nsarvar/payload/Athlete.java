package com.nsarvar.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Athlete {
    private String name;
    private Long totalScore;
    private String rank;
    private final List<Result> results = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addResult(Result result) {
        results.add(result);
        calculateTotalScore();
    }

    public List<Result> getResults() {
        return results;
    }

    public void calculateTotalScore() {
        totalScore = results.stream().
                map(Result::getPoints)
                .reduce(0L, Long::sum);
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return name.equals(athlete.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}


