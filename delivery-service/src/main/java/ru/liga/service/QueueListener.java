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
//
//    @SneakyThrows
//    @RabbitListener(queues = "myQueue1")
//    public void processMyQueue(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
//        log.info("Received from myQueue1 : " +  messageModel.getModelInfo());
//        log.info("Работа в it");
//    }
//
//    @SneakyThrows
//    @RabbitListener(queues = "myQueue2")
//    public void processMyQueue2(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
//        log.info("Received from myQueue2 : " +  messageModel.getModelInfo());
//        log.info("Работа не в it");
//    }
//
//    @SneakyThrows
//    @RabbitListener(queues = "myTopicQueue1")
//    public void processMyTopicQueue(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
//        log.info("Received from myTopicQueue1 : " +  messageModel.getModelInfo());
//        log.info("Работа не в it");
//    }
//
//    @SneakyThrows
//    @RabbitListener(queues = "myTopicQueue2")
//    public void processMyTopicQueue2(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
//        log.info("Received from myTopicQueue2 : " +  messageModel.getModelInfo());
//        log.info("Работа в it");
//    }
}
