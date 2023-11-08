package ru.liga.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestOrder {
    private Long customerId;

    private Long restaurantId;
	
    private List<RequestOrderItem> orderItems;
	
}

