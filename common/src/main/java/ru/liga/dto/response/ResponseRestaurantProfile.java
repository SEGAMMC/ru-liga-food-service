package ru.liga.dto.response;

import lombok.*;
import ru.liga.enums.RestaurantsStatus;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseRestaurantProfile {
	private long id;

	private String name;

	private String address;

	private RestaurantsStatus status;
	
}