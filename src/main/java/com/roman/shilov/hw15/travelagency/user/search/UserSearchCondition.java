package com.roman.shilov.hw15.travelagency.user.search;

import com.roman.shilov.hw15.travelagency.common.buisness.search.BaseSearchConditition;

import static com.roman.shilov.hw11.travelagency.common.solutions.utils.StringUtils.isNotBlank;

public class UserSearchCondition extends BaseSearchConditition {

    private String name;
    private String last;
    private UserOrderByField orderByField;

    public UserOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(UserOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public boolean searchByName(){
        return isNotBlank(this.getName());
    }

    public boolean searchByLast(){
        return isNotBlank(this.getLast());
    }

}
