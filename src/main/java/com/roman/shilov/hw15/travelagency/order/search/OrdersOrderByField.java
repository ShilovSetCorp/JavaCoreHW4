package com.roman.shilov.hw15.travelagency.order.search;

public enum OrdersOrderByField {
    PRICE("orderprice"), CITY("cityfromorder"), COUNTRY("countryfromorder"), USER("userfromorder");

    private String description;

    OrdersOrderByField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
