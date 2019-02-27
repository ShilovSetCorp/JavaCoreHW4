package com.roman.shilov.hw6.cities.repo.impl;

import com.roman.shilov.hw6.cities.domain.City;
import com.roman.shilov.hw6.cities.repo.CityRepo;
import com.roman.shilov.hw6.cities.search.CitySearchCondition;
import com.roman.shilov.hw6.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.roman.shilov.hw6.common.solutions.utils.StringUtils.isNotBlank;
import static com.roman.shilov.hw6.storage.Storage.cities;

public class CityMemoryArrayRepo implements CityRepo {

    private static final City[] EMPTY_CITIES_ARR = new City[0];
    private int cityIndex = -1;


    @Override
    public void add(City city) {
        if(cityIndex == cities.length - 1){
            City[] newArrCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newArrCities, 0, cities.length);
            cities = newArrCities;
        }

        cityIndex++;
        cities[cityIndex] = city;
    }

    @Override
    public City findById(long id) {
        Integer cityIndex = findCityIndexId(id);
        if(cityIndex != null){
            return cities[cityIndex];
        }
        return null;
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            boolean searchByPopulation = searchCondition.getPopulation() > 0;

            boolean searchByName = isNotBlank(searchCondition.getName());

            City[] result = new City[cities.length];
            int resultIndex = 0;

            for (City city : cities) {
                if (city != null) {
                    boolean found = true;

                    if (searchByPopulation) {
                        found = searchCondition.getPopulation() == city.getPopulation();
                    }

                    if (found && searchByName) {
                        found = searchCondition.getName().equals(city.getName());
                    }

                    if (found) {
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                City toReturn[] = new City[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return new ArrayList<>(Arrays.asList(toReturn));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Integer cityIndex = findCityIndexId(id);
        if(cityIndex != null){
            deleteCityByIndex(cityIndex);
        }
    }

    private void deleteCityByIndex(int index){
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(cities).forEach(System.out::println);
    }

    private Integer findCityIndexId(Long cityId){
        for (int i = 0; i < cities.length; i++) {
            if(cities[i].getId().equals(cityId)){
                return i;
            }
        }
        return null;
    }
}
