package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseCustomer {
	
	private String address;
	
	private double distance;
	
}
