package ru.liga.dto.response;

import lombok.*;
import ru.liga.enums.RestaurantStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseRestaurant {

	private Long id;

    private String name;

    private String address;

    private RestaurantStatus status;
	
}


