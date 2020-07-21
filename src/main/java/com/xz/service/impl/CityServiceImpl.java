package com.xz.service.impl;

import com.xz.domain.City;
import com.xz.repositorys.CityRepository;
import com.xz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Mono<City> addCity(City city) {
        Mono<City> save = cityRepository.save(city);
        return save;
    }

    @Override
    public Mono<Void> deleteCity(String id) {
        return cityRepository.deleteById(id);
    }

    @Override
    public Mono<City> getById(String id) {
        return cityRepository.findById(id);
    }


    @Override
    public Flux<City> getAllCity() {
        Flux<City> all = cityRepository.findAll();
        return all;
    }


}
