package ru.liga.dto.request;

import lombok.*;
import ru.liga.entity.Restaurant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestMenuItem {
	
    private long restaurantId;

    private String name;

    private double price;

    private String image;

    private String description;
	
}