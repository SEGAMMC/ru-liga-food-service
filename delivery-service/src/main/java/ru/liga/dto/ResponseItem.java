package ru.liga.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseItem {
    private double price;
    private int quantity;
    private String description;
    private String image;
}
