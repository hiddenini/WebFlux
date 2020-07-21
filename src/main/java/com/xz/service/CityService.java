package com.xz.service;


import com.xz.domain.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {
    /**
     * 添加一条city
     */
    Mono<City> addCity(City city);

    /**
     * 根据id删除city
     */
    Mono<Void> deleteCity(String id);

    /**
     * 根据id查询city
     */
    Mono<City> getById(String id);

    /**
     * 查询所有city
     */
    Flux<City> getAllCity();

}
