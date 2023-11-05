package ru.liga.dto.request;

import lombok.*;
import ru.liga.enums.RestaurantsStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestRestaurantStatus {
	
    private RestaurantsStatus status;
	
}