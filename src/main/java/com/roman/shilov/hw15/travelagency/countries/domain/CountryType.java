package com.roman.shilov.hw15.travelagency.countries.domain;

import java.util.HashMap;
import java.util.Map;

public enum CountryType {
    HOT, COLD;

    static Map<String, CountryType> stringCountryTypeMap = new HashMap<>();

    static {
        for (CountryType type : CountryType.values()) {
            stringCountryTypeMap.put(type.name(), type);
        }
    }

    public static CountryType getTypeByName(String name){
        return stringCountryTypeMap.get(name);
    }

    public static boolean isTypeExists(String type){
        return getTypeByName(type) != null;
    }

    public static boolean isTypeNotExists(String type){
        return !isTypeExists(type);
    }

}
