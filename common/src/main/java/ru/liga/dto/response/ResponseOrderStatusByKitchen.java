package ru.liga.dto.response;


import lombok.*;
import ru.liga.enums.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrderStatusByKitchen {

    private String uuid;
    private OrderStatus orderStatus;
}