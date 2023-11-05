package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrderItem {
	
    private double price;
    
	private int quantity;
    
	private String description;
    
	private String image;
}