package ru.liga.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMQConfig {

//    Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
    @Bean
    public Declarables myQueue() {
        Queue queueDirectFirst = new Queue("queueOrders", false);
        DirectExchange directExchange = new DirectExchange("pushOrdersDirectExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.new"));

    }

}
