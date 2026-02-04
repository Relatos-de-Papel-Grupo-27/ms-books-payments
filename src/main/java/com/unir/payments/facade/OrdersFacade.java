package com.unir.payments.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.unir.payments.facade.model.Order;

@Component
@Slf4j
public class OrdersFacade {

    private final WebClient webClient;

    public OrdersFacade(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Order getOrderById(Long orderId) {
        try {
            return webClient
                    .get()
                    .uri("lb://ms-books-orders/orders/{id}", orderId)
                    .retrieve()
                    .bodyToMono(Order.class)
                    .block();
        } catch (WebClientResponseException.ServiceUnavailable | WebClientResponseException.NotFound e) {
            log.warn("No se pudo obtener la orden {} desde el microservicio de orders: {}", orderId, e.getMessage());
            return null;
        } catch (Exception e) {
            log.warn("Error al obtener la orden {}: {}", orderId, e.getMessage());
            return null;
        }
    }
}
