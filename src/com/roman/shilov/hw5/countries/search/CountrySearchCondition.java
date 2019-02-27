package com.roman.shilov.hw5.countries.search;

import com.roman.shilov.hw5.common.buisness.search.BaseSearchConditition;

public class CountrySearchCondition extends BaseSearchConditition {
    private String name;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
