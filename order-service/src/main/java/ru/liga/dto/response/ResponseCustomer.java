package ru.liga.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseCustomer {

    private Long id;

    private String phone;

    private String email;

    private String address;

}