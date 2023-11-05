package ru.liga.dto.request;

import lombok.*;
import ru.liga.enums.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestOrderStatus {
	
    private OrderStatus status;
	
}