package com.rogelioorts.talks.service.impl;

import com.rogelioorts.talks.service.StockService;
import com.rogelioorts.talks.utils.MonoUtils;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

@Log
public class MockedStockService implements StockService {

    @Override
    public Mono<Void> lockStock(final String productId) {
        log.info(String.format("Stock locked. [productId=%s]", productId));
        return MonoUtils.randomDelayedMono();
    }

    @Override
    public Mono<Void> unlockStock(final String productId) {
        log.info(String.format("Stock unlocked. [productId=%s]", productId));
        return MonoUtils.randomDelayedMono();
    }

}
