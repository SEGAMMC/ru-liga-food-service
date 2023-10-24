package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseMenuItem {

    private Long id;

    private Long restaurantId;

    private String name;

    private double price;

    private String image;

    private String description;
	
}