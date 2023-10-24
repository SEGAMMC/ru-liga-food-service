package ru.liga.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestOrderItems {
	
	private int quantity;
    	
	private long menuItemId;

}

