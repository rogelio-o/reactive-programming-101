package com.rogelioorts.talks;

import com.rogelioorts.talks.repository.OrderRepository;
import com.rogelioorts.talks.repository.impl.MockedOrderRepository;
import com.rogelioorts.talks.service.EmailService;
import com.rogelioorts.talks.service.OrderService;
import com.rogelioorts.talks.service.StockService;
import com.rogelioorts.talks.service.impl.MockedEmailService;
import com.rogelioorts.talks.service.impl.MockedStockService;
import com.rogelioorts.talks.service.impl.OrderServiceImpl;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
public class MainApplication {

    public static void main(final String[] args) {
        final OrderService orderService = createOrderService();

        if (args.length == 0 || args[0].equals("unlockBatch")) {
            orderService.unlockCancelledAndWithErrorsOrdersStock()
                    .doOnError(e -> log.log(Level.SEVERE, e, () -> e.getMessage()))
                    .doOnSuccess(v -> log.info("Finished."))
                    .block();
        } else {
            orderService.lockOrderStock(args[0])
                    .doOnError(e -> log.log(Level.SEVERE, e, () -> e.getMessage()))
                    .doOnSuccess(v -> log.info("Finished."))
                    .block();
        }
    }

    private static OrderService createOrderService() {
        final StockService stockService = new MockedStockService();
        final EmailService emailService = new MockedEmailService();
        final OrderRepository orderRepository = new MockedOrderRepository();

        return new OrderServiceImpl(stockService, emailService, orderRepository);
    }

}
