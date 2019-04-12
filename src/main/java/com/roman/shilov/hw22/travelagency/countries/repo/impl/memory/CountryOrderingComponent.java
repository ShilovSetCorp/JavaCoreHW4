package com.roman.shilov.hw22.travelagency.countries.repo.impl.memory;

import com.roman.shilov.hw22.travelagency.countries.domain.BaseCountry;
import com.roman.shilov.hw22.travelagency.countries.search.CountryOrderByField;
import com.roman.shilov.hw22.travelagency.countries.search.CountrySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryOrderingComponent {

    public void applyOrdering(List<BaseCountry> countries, CountrySearchCondition searchCondition) {
        Comparator<BaseCountry> countryComparator = null;

        CountryOrderByField field = searchCondition.getOrderByField();

        switch (searchCondition.getOrderType()) {

            case SIMPLE: {
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }

            case COMPLEX: {
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(countryComparator != null) {
            switch (searchCondition.getOrderDirection()){
                case ASC:
                    Collections.sort(countries, countryComparator);
                    break;

                case DESC:
                    Collections.sort(countries, countryComparator.reversed());
                    break;
            }
        }
    }
}
