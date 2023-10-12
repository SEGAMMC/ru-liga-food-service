package ru.liga.controllers.response;

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
    private String secret_payment_url;
    private LocalDateTime estimated_time_of_arrival;

}
