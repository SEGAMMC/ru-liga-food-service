package ru.liga.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestMenuItemEdit {

    private Long id;

    private Long restaurantId;

    private String name;

    private double price;

    private String image;

    private String description;
	
}