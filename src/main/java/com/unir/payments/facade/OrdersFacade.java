package com.unir.payments.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.unir.payments.facade.model.Order;

@Component
public class OrdersFacade {

    private final WebClient webClient;

    public OrdersFacade(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Order getOrderById(Long orderId) {
        return webClient
                .get()
                .uri("lb://ms-books-orders/orders/{id}", orderId)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }
}
