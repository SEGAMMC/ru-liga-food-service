package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseRestaurant {
	
	private String address;
	
	private double distance;
	
}