package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseCustomerDelivery {
	
	private String address;
	
	private String distance;
	
}
