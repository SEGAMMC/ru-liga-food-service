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
        Queue queueDirectCouriers = new Queue("queueOrdersToKitchen", false);
        DirectExchange directExchange = new DirectExchange("notificationToKitchenDirectExchange");


        return new Declarables(queueDirectCouriers, directExchange,
                BindingBuilder.bind(queueDirectCouriers).to(directExchange)
                        .with("order.new"));
    }

    @Bean
    public Declarables myQueue2() {
        Queue queueDirectCouriers = new Queue("pushToCustomer", false);
        DirectExchange directExchange = new DirectExchange("notificationToCustomerDirectExchange");


        return new Declarables(queueDirectCouriers, directExchange,
                BindingBuilder.bind(queueDirectCouriers).to(directExchange)
                        .with("customer"));
    }

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}