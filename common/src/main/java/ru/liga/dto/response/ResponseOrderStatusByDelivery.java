package ru.liga.dto.response;


import lombok.*;
import ru.liga.enums.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrderStatusByDelivery {

    private String uuid;
    private OrderStatus orderStatus;
}