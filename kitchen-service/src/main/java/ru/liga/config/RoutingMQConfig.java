package ru.liga.config;

//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Declarables;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//@Configuration
public class RoutingMQConfig {

//    Declarables - Класс объединящий в себе очереди, тип обменника и байдинги(связи)
//    @Bean
//    public Declarables myQueue() {
//        Queue queueDirectFirst = new Queue("myQueue1", false);
//        Queue queueDirectSecond = new Queue("myQueue2", false);
//        DirectExchange directExchange = new DirectExchange("directExchange");
//
//        return new Declarables(queueDirectFirst, queueDirectSecond, directExchange,
//                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("job.it"),
//                BindingBuilder.bind(queueDirectSecond).to(directExchange).with("job.other"));
//    }
//
//    @Bean
//    public Declarables myQueueFanout() {
//        Queue queueTopicFirst = new Queue("myTopicQueue1", false);
//        Queue queueTopicSecond = new Queue("myTopicQueue2", false);
//        TopicExchange topicExchange = new TopicExchange("topicExchange");
//
//        return new Declarables(queueTopicFirst, queueTopicSecond, topicExchange,
//                BindingBuilder.bind(queueTopicFirst).to(topicExchange).with("*.other"),
//                BindingBuilder.bind(queueTopicSecond).to(topicExchange).with("*.it"));
//    }
}
