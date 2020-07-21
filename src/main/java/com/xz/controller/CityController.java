package com.xz.controller;

import com.xz.domain.City;
import com.xz.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 添加city
     */
    @RequestMapping(value = "/add")
    public Mono<City> add(@RequestBody City city) {
        return cityService.addCity(city);
    }

    /**
     * 根据city.id查询city,如果查询到数据则删除,否则返回404
     */
    @DeleteMapping(value = "/delete")
    public Mono<ResponseEntity<Void>> delete(@RequestParam String id) {
        return cityService.getById(id).
                flatMap(city -> cityService.deleteCity(id).
                        then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据city.id查询city,如果查询到数据则更新city信息,否则返回404
     */
    @PutMapping(value = "/update")
    public Mono<ResponseEntity<City>> update(@RequestBody City city) {
        return cityService.getById(city.getId()).
                /**
                 * flatMap的转换Function要求返回一个Publisher，这个Publisher代表一个作用于元素的异步的转换操作；而map仅仅是同步的元素转换操作。
                 */

                //如果需要操作数据,并且返回一个mono,使用flatMap
                flatMap(city1 -> {
                    city1.setCityName(city.getCityName());
                    city1.setProvinceId(city.getProvinceId());
                    city1.setDescription(city.getDescription());
                    return cityService.addCity(city1);
                })
                //如果需要转换数据使用map
                .map(city1 -> new ResponseEntity<City>(city1, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * 查询所有city
     */
    @GetMapping(value = "/findAll")
    public Flux<City> getAllCity() {
        return cityService.getAllCity();
    }

    /**
     * 根据id查询city,如果查询到数据则返回city否则返回404
     */
    @GetMapping(value = "/getById")
    public Mono<ResponseEntity<City>> getById(String id) {
        return cityService.getById(id).map(city -> new ResponseEntity<>(city, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
