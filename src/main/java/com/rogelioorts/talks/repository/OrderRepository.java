package com.rogelioorts.talks.repository;

import com.rogelioorts.talks.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {

    Flux<Order> findAllCancelled();

    Flux<Order> findAllWithErrors();

    Mono<Order> findById(String id);

}
