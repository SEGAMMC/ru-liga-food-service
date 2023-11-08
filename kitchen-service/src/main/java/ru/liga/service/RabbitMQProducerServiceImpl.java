package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQProducerServiceImpl{
//        implements RabbitMQProducerService
//{
//	private final String exchange = "DeliveryDirectExchange";
//	private final String routingKey = "delivery.all";
	
//    @Autowired
//    private final RabbitTemplate rabbitTemplate;
//	private final ObjectMapper objectMapper;


//	@Override
//    public void sendOrderToDelivery(String orderId) {
//        rabbitTemplate.convertAndSend(exchange, routingKey, orderId);
//        log.info("Message has been sanded");
//		System.out.println("Message has been sanded");
//    }
}
