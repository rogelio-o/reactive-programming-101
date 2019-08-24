package com.rogelioorts.talks.service.impl;

import com.rogelioorts.talks.service.EmailService;
import com.rogelioorts.talks.utils.MonoUtils;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

@Log
public class MockedEmailService implements EmailService {

    @Override
    public Mono<Void> sendOrderCanceledEmail(final String email) {
        log.info(String.format("Order canceled email sent [email=%s]", email));
        return MonoUtils.randomDelayedMono();
    }

    @Override
    public Mono<Void> sendOrderReservedEmail(final String email) {
        log.info(String.format("Order reserved email sent [email=%s]", email));
        return MonoUtils.randomDelayedMono();
    }

}
