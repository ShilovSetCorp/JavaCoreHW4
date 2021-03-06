package com.roman.shilov.hw17.travelagency.countries.service.impl;

import com.roman.shilov.hw17.travelagency.cities.domain.City;
import com.roman.shilov.hw17.travelagency.cities.repo.CityRepo;
import com.roman.shilov.hw17.travelagency.countries.domain.BaseCountry;
import com.roman.shilov.hw17.travelagency.countries.repo.CountryRepo;
import com.roman.shilov.hw17.travelagency.countries.search.CountrySearchCondition;
import com.roman.shilov.hw17.travelagency.countries.service.CountryService;
import com.roman.shilov.hw17.travelagency.countries.service.exceptions.CountryIsContainedInSomeOrdersException;
import com.roman.shilov.hw17.travelagency.order.domain.Order;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.roman.shilov.hw17.travelagency.storage.Storage.ordersList;

public class CountryDefaultService implements CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public BaseCountry insert(BaseCountry baseCountry) {

        countryRepo.insert(baseCountry);

        if(baseCountry.getCities() != null){
            for(City city : baseCountry.getCities()){
                city.setCountryId(baseCountry.getId());
                cityRepo.insert(city);
            }
        }
        return baseCountry;
    }

    @Override
    public void insert(Collection<BaseCountry> items) {
        if (!items.isEmpty()) {
            countryRepo.insert(items);

            for(BaseCountry country : items) {
               if(country.getCities() != null) {
                    country.getCities().replaceAll(city -> {
                        city.setCountryId(country.getId());
                        return city;
                    });
                   cityRepo.insert(country.getCities());
               }
           }
        }
    }

    @Override
    public BaseCountry findById(Long id) {
        if(id != null){
            return countryRepo.findById(id);
        }else {
            return null;
        }
    }

    @Override
    public void update(BaseCountry baseCountry) {
        countryRepo.update(baseCountry);
    }

    @Override
    public void delete(BaseCountry baseCountry) {
        if(baseCountry.getId() != null){
            try {
                //Check if Country is contained in some orders
                for(Order order: ordersList) {
                    if (order.getBaseCountry().getId().equals(baseCountry.getId())) {
                        //throw exception if it is so
                        throw new CountryIsContainedInSomeOrdersException("There are still orders which contains country that should be deleted", 30);
                    }
                }
            }catch (CountryIsContainedInSomeOrdersException e) {
                System.out.println(e.getMessage());
            } finally {
                Iterator<Order> it = ordersList.iterator();
                while (it.hasNext()) {
                    if (it.next().getBaseCountry().getId().equals(baseCountry.getId())) {
                        it.remove();
                    }
                }
                this.deleteById(baseCountry.getId());
            }
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