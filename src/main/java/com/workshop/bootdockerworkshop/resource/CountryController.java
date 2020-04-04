package com.workshop.bootdockerworkshop.resource;

import com.workshop.bootdockerworkshop.bussiness.dto.CountryDetails;
import com.workshop.bootdockerworkshop.bussiness.service.CountryService;
import com.workshop.bootdockerworkshop.exception.CounteryCodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class CountryController {

    private transient CountryService countryService;


    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("/{code}")
    public ResponseEntity<?> getCountryDetailsByCode(@PathVariable("code") String countryCode) {
        CountryDetails countryDetails = this.countryService.getCountryDetails(countryCode);
        if (null != countryDetails) {
            return ResponseEntity.ok(countryDetails);
        }
        throw new CounteryCodeNotFoundException();
    }
}
