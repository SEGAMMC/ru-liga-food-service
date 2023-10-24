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
	
    private ResponseRestaurant restaurant;
		
    private ResponseCustomer customer;
	
	private double payment;
	
}