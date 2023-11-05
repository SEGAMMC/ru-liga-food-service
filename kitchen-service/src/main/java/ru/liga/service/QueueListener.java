package ru.liga.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

//TODO как определяется очередь
//    @SneakyThrows
//    @RabbitListener(queues = "newOrderToId")
//    public void processMyQueue(String message) {
		//Получили orderId



//    }

}
