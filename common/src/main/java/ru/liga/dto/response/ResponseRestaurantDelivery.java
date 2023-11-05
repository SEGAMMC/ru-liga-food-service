package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseRestaurantDelivery {
	
	private String address;
	
	private String distance;
	
}