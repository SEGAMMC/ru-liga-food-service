package ru.liga.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.MessageModel;
import ru.liga.service.CarService;

@RestController
@RequestMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/send")
    public void sendMessageToRabbit(@RequestBody MessageModel messageModel) {
        carService.sendCarInfo(messageModel);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
