package ru.liga.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrderAccept {
	
    private long id;
	
    private String secretPaymentUrl;
	
    private LocalDateTime estimatedTimeOfArrival;

}