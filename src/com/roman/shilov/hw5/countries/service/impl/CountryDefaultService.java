package com.roman.shilov.hw5.countries.service.impl;

import com.roman.shilov.hw5.cities.domain.City;
import com.roman.shilov.hw5.cities.repo.CityRepo;
import com.roman.shilov.hw5.common.buisness.search.BaseSearchConditition;
import com.roman.shilov.hw5.countries.domain.Country;
import com.roman.shilov.hw5.countries.repo.CountryRepo;
import com.roman.shilov.hw5.countries.search.CountrySearchCondition;
import com.roman.shilov.hw5.countries.service.CountryService;

public class CountryDefaultService implements CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(Country country) {
        countryRepo.add(country);

        if(country.getCities() != null){
            for(City city : country.getCities()){
                cityRepo.add(city);
            }
        }
    }

    @Override
    public Country find(BaseSearchConditition searchCondition) {
        if(searchCondition instanceof CountrySearchCondition){
            return findById(searchCondition.getId());
        }else{
            return null;
        }
    }

    @Override
    public Country findById(Long id) {
        if(id != null){
            return countryRepo.findById(id);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Country country) {
        if(country.getId() != null){
            this.deleteById(country.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if(id != null){
            countryRepo.deleteById(id);
        }
    }



    @Override
    public void printAll() {
        countryRepo.printAll();
    }
}
