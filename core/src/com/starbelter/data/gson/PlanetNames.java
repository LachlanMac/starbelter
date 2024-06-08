package com.starbelter.data.gson;

import java.util.List;

public class PlanetNames {
    private List<String> Prefixes;
    private List<String> Middlefixes;
    private List<String> Suffixes;

    // Getters and setters
    public List<String> getPrefixes() {
        return Prefixes;
    }

    public void setPrefixes(List<String> prefixes) {
        Prefixes = prefixes;
    }

    public List<String> getMiddlefixes() {
        return Middlefixes;
    }

    public void setMiddlefixes(List<String> middlefixes) {
        Middlefixes = middlefixes;
    }

    public List<String> getSuffixes() {
        return Suffixes;
    }

    public void setSuffixes(List<String> suffixes) {
        Suffixes = suffixes;
    }
}