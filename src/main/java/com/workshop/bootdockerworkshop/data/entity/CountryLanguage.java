package com.workshop.bootdockerworkshop.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.*;

@Entity
@Table(name = "country_language")
public class CountryLanguage {


    @Id
    @Column(name="language")
    private String language;
    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;
    @Column(name = "is_official")
    private Boolean isOfficial;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }
}
