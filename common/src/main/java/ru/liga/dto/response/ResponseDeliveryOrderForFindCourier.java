package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseDeliveryOrderForFindCourier {
	
	private String uuid;

    private String addressRestaurant;

    private String addressCustomer;

    private String coordsCourier;
	
}