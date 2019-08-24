package com.rogelioorts.talks.repository.impl;

import com.rogelioorts.talks.entity.Order;
import com.rogelioorts.talks.repository.OrderRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class MockedOrderRepository implements OrderRepository {

    @Override
    public Flux<Order> findAllCancelled() {
        return Flux.just(
                createOrder("a@a.com", new Order.Line("1"), new Order.Line("2")),
                createOrder("b@b.com", new Order.Line("3"))
        );
    }

    private Order createOrder(final String email, final Order.Line... lines) {
        final Order order = new Order();
        order.setEmail(email);
        order.setLines(Arrays.asList(lines));

        return order;
    }

    @Override
    public Flux<Order> findAllWithErrors() {
        return Flux.just(
                createOrder("c@c.com", new Order.Line("4")),
                createOrder("d@d.com", new Order.Line("5"), new Order.Line("6"))
        );
    }

    @Override
    public Mono<Order> findById(final String id) {
        return Mono.just(createOrder("e@e.com", new Order.Line("7"), new Order.Line("8")));
    }

}
