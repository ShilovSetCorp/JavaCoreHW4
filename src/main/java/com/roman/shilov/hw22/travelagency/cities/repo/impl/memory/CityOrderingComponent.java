package com.roman.shilov.hw22.travelagency.cities.repo.impl.memory;

import com.roman.shilov.hw22.travelagency.cities.domain.City;
import com.roman.shilov.hw22.travelagency.cities.search.CityOrderByField;
import com.roman.shilov.hw22.travelagency.cities.search.CitySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityOrderingComponent {

    public void applyOrdering(List<City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;

        CityOrderByField field = citySearchCondition.getOrderByField();
        switch (citySearchCondition.getOrderType()) {

            case SIMPLE:{
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(cityComparator != null){
            switch (citySearchCondition.getOrderDirection()){
                case ASC:
                    Collections.sort(cities, cityComparator);
                    break;

                case DESC:
                    Collections.sort(cities, cityComparator.reversed());
                    break;
            }
        }
    }

}
