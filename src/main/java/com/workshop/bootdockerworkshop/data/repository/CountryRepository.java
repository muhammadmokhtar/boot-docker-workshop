package com.workshop.bootdockerworkshop.data.repository;

import com.workshop.bootdockerworkshop.data.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.ConnectException;
import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

}
