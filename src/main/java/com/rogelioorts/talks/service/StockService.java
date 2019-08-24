package com.rogelioorts.talks.service;

import reactor.core.publisher.Mono;

public interface StockService {

    Mono<Void> lockStock(String productId);

    Mono<Void> unlockStock(String productId);

}
