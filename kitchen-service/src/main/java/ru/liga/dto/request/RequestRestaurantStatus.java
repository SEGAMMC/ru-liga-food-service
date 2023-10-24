package ru.liga.dto.request;

import lombok.*;
import ru.liga.enums.RestaurantStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestRestaurantStatus {
	
    private RestaurantStatus status;
	
}