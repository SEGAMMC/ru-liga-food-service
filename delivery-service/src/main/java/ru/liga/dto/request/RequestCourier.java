package ru.liga.dto.request;

import lombok.*;
import ru.liga.enums.CourierStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestCourier {

    private String phone;

    private CourierStatus status;

}