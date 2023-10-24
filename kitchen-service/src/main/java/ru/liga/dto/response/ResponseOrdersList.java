package ru.liga.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrdersList {
	
    private List<ResponseOrder> orders;
    
}