package com.xz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class HelloController {

    @RequestMapping(value = "/flux")
    public Flux<String> flux() {
        return Flux.just("hello flux");
    }

    // 普通的SpringMVC方法
    @GetMapping("/1")
    private String get1() {
        log.info("get1 start");
        String result = createStr();
        log.info("get1 end.");
        return result;
    }


    /**
     * 从调用者(浏览器)的角度而言，是感知不到有什么变化的，因为都是得等待5s才返回数据。但是，从服务端的日志我们可以看出
     * <p>
     * WebFlux是直接返回Mono对象的(而不是像SpringMVC一直同步阻塞5s，线程才返回)。
     * <p>
     * 这正是WebFlux的好处：能够以固定的线程来处理高并发（充分发挥机器的性能）。
     */

    // WebFlux(返回的是Mono)
    @GetMapping("/2")
    private Mono<String> get2() {
        log.info("get2 start");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get2 end.");
        return result;
    }

    // 阻塞5秒钟
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return "some string";
    }
}
