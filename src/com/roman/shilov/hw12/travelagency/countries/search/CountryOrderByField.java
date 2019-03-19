package com.roman.shilov.hw12.travelagency.countries.search;

public enum CountryOrderByField {
    NAME("countryname"), LANGUAGE("countrylanguage");

    CountryOrderByField(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
