package com.roman.shilov.hw15.travelagency.user.repo.impl;

import com.roman.shilov.hw15.travelagency.user.domain.User;
import com.roman.shilov.hw15.travelagency.user.search.UserOrderByField;
import com.roman.shilov.hw15.travelagency.user.search.UserSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserOrderingComponent {

    public void applyOrdering(List<User> users, UserSearchCondition searchCondition){
        Comparator<User> userComparator = null;

        UserOrderByField field = searchCondition.getOrderByField();

        switch (searchCondition.getOrderType()){

            case SIMPLE: {
                userComparator = UserComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                userComparator = UserComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(userComparator != null){
            switch (searchCondition.getOrderDirection()){
                case ASC: {
                    Collections.sort(users, userComparator);
                    break;
                }
                case DESC: {
                    Collections.sort(users, userComparator.reversed());
                    break;
                }
            }
        }
    }
}
