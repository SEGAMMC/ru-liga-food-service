package ru.liga.dto.response;

import lombok.*;
import ru.liga.enums.CourierStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseCourier {
	
    private Long id;

    private String phone;

    private CourierStatus status;

    private String coordinates;

}