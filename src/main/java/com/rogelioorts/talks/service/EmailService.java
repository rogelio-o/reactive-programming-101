package com.rogelioorts.talks.service;

import reactor.core.publisher.Mono;

public interface EmailService {

    Mono<Void> sendOrderCanceledEmail(String email);

    Mono<Void> sendOrderReservedEmail(String email);

}
