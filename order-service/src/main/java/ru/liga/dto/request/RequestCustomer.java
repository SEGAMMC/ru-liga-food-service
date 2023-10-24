package ru.liga.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestCustomer {

    private String phone;

    private String email;

    private String address;

}