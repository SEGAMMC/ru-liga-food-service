package ru.liga.dto.request;

import lombok.*;
import ru.liga.enums.RestaurantsStatus;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestRestaurant {

    private String name;

    private String address;

    private RestaurantsStatus status;
	
}


