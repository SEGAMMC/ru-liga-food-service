package ru.liga.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestUpdatePriceMenuItem {

    private double price;

}