package com.rogelioorts.talks.service.impl;

import com.rogelioorts.talks.entity.Order;
import com.rogelioorts.talks.repository.OrderRepository;
import com.rogelioorts.talks.service.EmailService;
import com.rogelioorts.talks.service.OrderService;
import com.rogelioorts.talks.service.StockService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StockService stockService;

    private final EmailService emailService;

    private final OrderRepository orderRepository;

    @Override
    public Mono<Void> unlockCancelledAndWithErrorsOrdersStock() {
        final Flux<Order> cancelledOrders = orderRepository.findAllCancelled();
        final Flux<Order> withErrorsOrders = orderRepository.findAllWithErrors();

        return Flux.merge(cancelledOrders, withErrorsOrders)
                .flatMap(this::unlockOrderStock)
                .then();
    }

    private Mono<Void> unlockOrderStock(final Order order) {
        return Flux.fromIterable(order.getLines())
                .flatMap(orderLine -> stockService.unlockStock(orderLine.getProductId()))
                .then(Mono.defer(() -> emailService.sendOrderCanceledEmail(order.getEmail())));
    }

    @Override
    public Mono<Void> lockOrderStock(final String id) {
        return orderRepository.findById(id)
                .flatMap(this::lockOrderStock);
    }

    private Mono<Void> lockOrderStock(final Order order) {
        return Flux.fromIterable(order.getLines())
                .flatMap(orderLine -> stockService.lockStock(orderLine.getProductId()))
                .then(Mono.defer(() -> emailService.sendOrderReservedEmail(order.getEmail())));
    }

}
