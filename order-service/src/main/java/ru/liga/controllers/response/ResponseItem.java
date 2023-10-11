package ru.liga.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseItem {
    private double price;
    private int quantity;
    private String description;
    private String image;
}
