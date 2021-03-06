package com.roman.shilov.hw22.travelagency.storage.initialisation.dom;

import com.roman.shilov.hw22.travelagency.cities.domain.City;
import com.roman.shilov.hw22.travelagency.common.solutions.utils.Months;
import com.roman.shilov.hw22.travelagency.countries.domain.BaseCountry;
import com.roman.shilov.hw22.travelagency.countries.domain.ColdCountry;
import com.roman.shilov.hw22.travelagency.countries.domain.CountryType;
import com.roman.shilov.hw22.travelagency.countries.domain.HotCountry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.roman.shilov.hw12.travelagency.common.solutions.utils.xml.XmlDomUtils.*;

public class XmlReader{// implements SourceReader<List<BaseCountry>> {

   //@Override
    public static List<BaseCountry> readDataFromFileViaLambda(String filePath) throws Exception {
        if(!new File(filePath).exists() || new File(filePath).isDirectory()){
            throw new Exception("No such file");
        }

        Document document = getDocument(filePath);
        Element root = getOnlyElement(document, "countries");

        NodeList countries = root.getElementsByTagName("country");
        List<BaseCountry> parsedCountries = new ArrayList<>();


        for (int i = 0; i < countries.getLength(); i++) {
            parsedCountries.add(getCountryFromXml(countries.item(i)));
        }

        return parsedCountries;
    }

    private static BaseCountry getCountryFromXml(Node xmlCountry) throws Exception{

        String type = ((Element)xmlCountry).getAttribute("type");
        if(CountryType.isTypeExists(type)) {
            BaseCountry country = null;
            switch (CountryType.valueOf(type)){
                case HOT: {
                    country = new HotCountry();
                    HotCountry hotCountry = (HotCountry)country;
                    hotCountry.setHottestMonth(Months.valueOf(getOnlyElementTextContent((Element)xmlCountry, "month")));
                    hotCountry.setAverageTemp(Integer.parseInt(getOnlyElementTextContent((Element)xmlCountry, "average")));
                    break;
                }

                case COLD: {
                    country = new ColdCountry();
                    ColdCountry coldCountry = (ColdCountry)country;
                    coldCountry.setTheMostSnowingMonth(Months.valueOf(getOnlyElementTextContent((Element)xmlCountry, "month")));
                    coldCountry.setAverageSnowLevel(Integer.parseInt(getOnlyElementTextContent((Element)xmlCountry, "average")));
                    coldCountry.setPolarNight("polarnight".equals(getOnlyElementTextContent((Element)xmlCountry, "polarnight")));
                    break;
                }
            }

            country.setName(getOnlyElementTextContent((Element)xmlCountry, "name"));
            country.setLanguage(getOnlyElementTextContent((Element)xmlCountry, "language"));
            country.setTelephoneCode(getOnlyElementTextContent((Element)xmlCountry, "telephoneCode"));
            NodeList cities = ((Element)xmlCountry).getElementsByTagName("city");
            if (cities.getLength() > 0) {
                country.setCities(new ArrayList<>());

                for (int i = 0; i < cities.getLength(); i++) {
                    City city  = getCityFromXml((Element)cities.item(i));
                    country.getCities().add(city);
                }
            }

            return country;
        }else {
            throw new Exception("no type was in XML");
        }
    }

    private static City getCityFromXml(Element xmlCity){
        City city = new City();

        city.setName(getOnlyElementTextContent(xmlCity, "name"));
        city.setPopulation(Integer.parseInt(getOnlyElementTextContent(xmlCity, "population")));
        city.setCapital("capital".equals(getOnlyElementTextContent(xmlCity, "iscapital")));

        return city;
    }

}


