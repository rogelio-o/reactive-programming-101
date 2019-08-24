package com.rogelioorts.talks.service;

import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Void> unlockCancelledAndWithErrorsOrdersStock();

    Mono<Void> lockOrderStock(String id);

}
