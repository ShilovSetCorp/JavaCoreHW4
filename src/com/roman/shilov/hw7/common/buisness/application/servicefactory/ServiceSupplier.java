package com.roman.shilov.hw7.common.buisness.application.servicefactory;


import com.roman.shilov.hw7.cities.service.CityService;
import com.roman.shilov.hw7.common.buisness.application.StorageType;
import com.roman.shilov.hw7.countries.service.CountryService;
import com.roman.shilov.hw7.order.service.OrderService;
import com.roman.shilov.hw7.user.service.UserService;

public final class ServiceSupplier{
    private static volatile ServiceSupplier SUPPLIER;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier setSupplier(){
        return SUPPLIER;
    }

    public static ServiceSupplier newSupplier(StorageType storageType){
        if(SUPPLIER == null){
            synchronized (ServiceSupplier.class){
                if(SUPPLIER == null){
                    SUPPLIER = new ServiceSupplier(storageType);
                }
            }
        }
        return SUPPLIER;
    }

    private ServiceSupplier(StorageType storageType){
        initServiceFactory(storageType);
    }

    private void initServiceFactory(StorageType storageType){
        switch (storageType){
            case MEMORY_ARRAY: {
                serviceFactory = new MemoryArrayServiceFactory();
            }
            default: {
                serviceFactory = new MemoryCollectionServiceFactory();
            }
        }
    }

    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }

    public UserService getUserService() {
        return serviceFactory.getUserService();
    }

    public CountryService getCountryService() {
        return serviceFactory.getCountryService();
    }

    public CityService getCityService() {
        return serviceFactory.getCityService();
    }

}