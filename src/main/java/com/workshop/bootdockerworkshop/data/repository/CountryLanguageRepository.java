package com.workshop.bootdockerworkshop.data.repository;

import com.workshop.bootdockerworkshop.data.entity.Country;
import com.workshop.bootdockerworkshop.data.entity.CountryLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface CountryLanguageRepository extends CrudRepository<CountryLanguage, String> {
    default List<CountryLanguage> findLanguagesByCountry(Country country) {
        List<CountryLanguage> languageList = new ArrayList<>();
        this.findCountryLanguagesByCountry(country).forEach(countryLanguage -> languageList.add(countryLanguage));
        return languageList;
    }

    Iterable<CountryLanguage> findCountryLanguagesByCountry(Country country);
}
