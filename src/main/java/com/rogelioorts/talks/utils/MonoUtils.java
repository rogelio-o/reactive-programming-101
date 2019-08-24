package com.rogelioorts.talks.utils;

import reactor.core.publisher.Mono;

import java.time.Duration;

public final class MonoUtils {

    private MonoUtils() {}

    public static Mono<Void> randomDelayedMono() {
        return Mono.delay(Duration.ofMillis((long) (Math.random() * 1000)))
                .then();
    }

}
