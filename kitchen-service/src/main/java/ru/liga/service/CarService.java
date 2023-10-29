package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.entity.MessageModel;

@Service
@RequiredArgsConstructor
public class CarService {

    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    private final ObjectMapper objectMapper;

    public void sendCarInfo(MessageModel messageModel) {
        String carInfoInLine = tryToSerialyzeMessageAsString(messageModel);
        if (messageModel.getModelInfo().equals("BMW")) {
            rabbitMQProducerService.sendMessage(carInfoInLine, "job.it");
        } else {
            rabbitMQProducerService.sendMessage(carInfoInLine, "job.other");
        }
    }

    private String tryToSerialyzeMessageAsString(MessageModel messageModel) {
        String carInfoInLine = null;
        try {
            carInfoInLine = objectMapper.writeValueAsString(messageModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return carInfoInLine;
    }
}
