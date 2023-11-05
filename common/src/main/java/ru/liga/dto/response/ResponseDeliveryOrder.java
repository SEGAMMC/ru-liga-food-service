package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseDeliveryOrder {
	
	private long orderId;
	
    private ResponseRestaurantDelivery restaurant;
		
    private ResponseCustomerDelivery customer;
	
	private String payment;
	
}