package com.roman.shilov.hw15.travelagency.cities.service;

import com.roman.shilov.hw15.travelagency.cities.repo.impl.CityMemoryArrayRepo;
import com.roman.shilov.hw15.travelagency.cities.repo.impl.CityMemoryCollectionRepo;
import com.roman.shilov.hw15.travelagency.cities.service.impl.CityDefaultService;
import com.roman.shilov.hw15.travelagency.common.buisness.application.StorageType;

public final class CityServiceCreator {

    public CityServiceCreator() {
    }

    public static CityService getCityService(StorageType storageType){
        switch (storageType){

            case MEMORY_ARRAY:
                return new CityDefaultService(new CityMemoryArrayRepo());

            case MEMORY_COLLECTION:
                return new CityDefaultService(new CityMemoryCollectionRepo());

            default: return null;
        }
    }
}
