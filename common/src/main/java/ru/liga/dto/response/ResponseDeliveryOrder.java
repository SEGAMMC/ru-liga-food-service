package ru.liga.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseDeliveryOrder {
	
	private String uuid;
	
    private ResponseRestaurantDelivery restaurant;
		
    private ResponseCustomerDelivery customer;
	
	private String payment;

    @JsonIgnore
    private double sumDistance;
}