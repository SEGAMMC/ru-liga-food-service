package ru.liga.dto.request;

import lombok.*;
import ru.liga.dto.response.ResponseCustomerDelivery;
import ru.liga.dto.response.ResponseRestaurantDelivery;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestDeliveryOrder {
	
	private String uuid;
	
    private ResponseRestaurantDelivery restaurant;
		
    private ResponseCustomerDelivery customer;
	
	private String payment;
	
}