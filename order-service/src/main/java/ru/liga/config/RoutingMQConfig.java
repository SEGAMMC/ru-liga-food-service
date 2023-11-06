package ru.liga.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RoutingMQConfig {


    @Bean
    public Declarables myQueue() {
        Queue queueDirectCouriers = new Queue("courierSearchQueueToNotification", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectCouriers, directExchange,
            BindingBuilder.bind(queueDirectCouriers).to(directExchange).with("courier_search_to_notification")
        );
    }

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

}
