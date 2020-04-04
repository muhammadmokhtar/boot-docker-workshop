package com.workshop.bootdockerworkshop.bussiness.service;

import com.workshop.bootdockerworkshop.bussiness.dto.CountryDetails;
import com.workshop.bootdockerworkshop.data.entity.Country;
import com.workshop.bootdockerworkshop.data.entity.CountryLanguage;
import com.workshop.bootdockerworkshop.data.repository.CountryLanguageRepository;
import com.workshop.bootdockerworkshop.data.repository.CountryRepository;
import com.workshop.bootdockerworkshop.exception.CounteryCodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    private CountryLanguageRepository countryLanguageRepository;

    @Autowired
    public CountryService(CountryLanguageRepository countryLanguageRepository, CountryRepository countryRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryRepository = countryRepository;
    }

    public CountryDetails getCountryDetails(String countryCode) {
        CountryDetails countryDetails = null;
        CountryLanguage countryLanguage = null;
        Optional<Country> country = this.countryRepository.findById(countryCode);
        if(country.isPresent()) {
            countryLanguage = this.countryLanguageRepository.findLanguagesByCountry(country.get()).stream()
                    .filter(language -> language.getOfficial()).findFirst().get();
            countryDetails = mapToCountryDetails(country.get());
            countryDetails.setCountry_language(countryLanguage.getLanguage());
        }
        return countryDetails;
    }

    private CountryDetails mapToCountryDetails(Country country) {
        CountryDetails countryDetails = new CountryDetails();
        countryDetails.setContinent(country.getContinent());
        countryDetails.setLife_expectancy(country.getLifeExpectancy());
        countryDetails.setName(country.getName());
        countryDetails.setPopulation(country.getPopulation());
        return countryDetails;
    }

}
