package ru.liga.dto.response;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrder {
	
    private long id;
	
    private ResponseRestaurantName restaurant;
	
    private LocalDateTime timestamp;
	
    private List<ResponseOrderItem> items;

}